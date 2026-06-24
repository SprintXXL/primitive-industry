package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.recipe.FactoryRecipeMatcher;
import com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry;
import com.SprintXXL.primitiverecipeapi.api.RecipeResourceResolver;
import com.SprintXXL.primitiverecipeapi.factory.FactoryRecipe;
import com.SprintXXL.primitiverecipeapi.factory.FactoryRecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

public class TileEntityFactoryBase extends TileEntity implements ITickable {

    private final ItemStackHandler inventory;
    private String factoryID;
    private FactoryRecipe currentRecipe;
    private String currentRecipeID;

    private int progress = 0;
    private int maxProgress = 0;

    public TileEntityFactoryBase() {
        this.inventory = new ItemStackHandler(2);
    }

    public IItemHandler getInventory() {
        return inventory;
    }

    public Factory getFactory() {
        return FactoryRegistry.getFactory(factoryID);
    }

    public String getFactoryID() {
        return factoryID;
    }

    public void setFactory(Factory factory) {
        if (factory == null) {
            this.factoryID = null;
            return;
        }

        this.factoryID = factory.getID();
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public boolean isProcessing() {
        return currentRecipeID != null;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setTag("Inventory", inventory.serializeNBT());

        if (factoryID != null) {
            compound.setString("FactoryID", factoryID);
        }

        if (currentRecipeID != null) {
            compound.setString("CurrentRecipeID", currentRecipeID);
        }

        compound.setInteger("Progress", progress);
        compound.setInteger("MaxProgress", maxProgress);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey("Inventory")) {
            inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
        }

        if (compound.hasKey("FactoryID")) {
            factoryID = compound.getString("FactoryID");
        }

        if (compound.hasKey("CurrentRecipeID")) {
            currentRecipeID = compound.getString("CurrentRecipeID");
        }

        progress = compound.getInteger("Progress");
        maxProgress = compound.getInteger("MaxProgress");
    }

    @Override
    public void update() {

        if (world.isRemote) {
            return;
        }

        Factory factory = getFactory();

        if (factory == null) {
            return;
        }

        restoreCurrentRecipe();

        List<Integer> inputSlots = factory.getInputSlots();
        List<Integer> outputSlots = factory.getOutputSlots();

        if (inputSlots.isEmpty() || outputSlots.isEmpty()) {
            return;
        }

        if (currentRecipe == null) {

            List<ItemStack> inputStacks = getInputStacks(factory);

            FactoryRecipe recipe = FactoryRecipeMatcher.findMatchingRecipe(factoryID, inputStacks);

            if (recipe == null) {
                progress = 0;
                maxProgress = 0;
                return;
            }

            consumeInputs(factory, recipe);

            currentRecipe = recipe;
            currentRecipeID = recipe.getRecipeID();
            maxProgress = recipe.getDurationTicks();
            progress = 0;
        }

        if (progress < maxProgress) {
            progress++;
        }

        if (progress < maxProgress) {
            return;
        }

        ItemStack newOutput = RecipeResourceResolver.createStack(currentRecipe.getBasicFactoryData().getOutputs().get(0));

        if (!canFitOutput(newOutput)) {
            progress = maxProgress;
            return;
        }

        insertOutput(newOutput);

        currentRecipe = null;
        currentRecipeID = null;
        progress = 0;
        maxProgress = 0;
    }

    private boolean canFitOutput(ItemStack newOutput) {

        Factory factory = getFactory();

        if (factory == null) {
            return false;
        }

        int outputSlot = factory.getOutputSlots().get(0);

        if (outputSlot < 0) {
            return false;
        }

        ItemStack existingOutput = inventory.getStackInSlot(outputSlot);

        if (existingOutput.isEmpty()) {
            return true;
        }

        if (!ItemStack.areItemsEqual(existingOutput, newOutput)) {
            return false;
        }

        if (!ItemStack.areItemStackTagsEqual(existingOutput, newOutput)) {
            return false;
        }

        int maxStackSize = Math.min(
                existingOutput.getMaxStackSize(),
                inventory.getSlotLimit(outputSlot)
        );

        return existingOutput.getCount() + newOutput.getCount() <= maxStackSize;
    }

    private void insertOutput(ItemStack newOutput) {

        Factory factory = getFactory();

        if (factory == null) {
            return;
        }

        int outputSlot = factory.getOutputSlots().get(0);

        if (outputSlot < 0) {
            return;
        }

        ItemStack existingOutput = inventory.getStackInSlot(outputSlot);

        if (existingOutput.isEmpty()) {
            inventory.setStackInSlot(outputSlot, newOutput);
            return;
        }

        existingOutput.grow(newOutput.getCount());
    }

    private Factory getValidFactory() {
        return getFactory();
    }

    private List<ItemStack> getInputStacks(Factory factory) {

        List<ItemStack> inputStacks = new ArrayList<>();

        for (int slot : factory.getInputSlots()) {
            inputStacks.add(inventory.getStackInSlot(slot));
        }

        return inputStacks;
    }

    private void consumeInputs(Factory factory, FactoryRecipe recipe) {

        List<Integer> inputSlots = factory.getInputSlots();

        for (int i = 0; i < recipe.getBasicFactoryData().getInputs().size(); i++) {

            int slot = inputSlots.get(i);

            int count = recipe.getBasicFactoryData().getInputs().get(i).getCount();

            inventory.getStackInSlot(slot).shrink(count);
        }
    }

    private void restoreCurrentRecipe() {

        if (currentRecipe != null || currentRecipeID == null) {
            return;
        }

        currentRecipe = FactoryRecipeRegistry.getRecipe(currentRecipeID);

        if (currentRecipe == null) {
            currentRecipeID = null;
            progress = 0;
            maxProgress = 0;
        }
    }
}