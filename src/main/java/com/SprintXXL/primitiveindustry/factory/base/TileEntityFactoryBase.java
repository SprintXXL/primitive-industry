package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.PrimitiveIndustry;
import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.recipe.FactoryRecipeMatcher;
import com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry;
import com.SprintXXL.primitiverecipeapi.factory.FactoryRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFactoryBase extends TileEntity implements ITickable {

    private final ItemStackHandler inventory;
    private String factoryID;
    private FactoryRecipe currentRecipe;
    private String currentRecipeID;

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

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

        if (getFactory() == null) {
            return;
        }

        ItemStack inputStack = inventory.getStackInSlot(INPUT_SLOT);

        if (currentRecipe == null) {

            FactoryRecipe recipe = FactoryRecipeMatcher.findMatchingRecipe(factoryID, inputStack);

            if (recipe == null) {
                progress = 0;
                maxProgress = 0;
                return;
            }

            inputStack.shrink(1);

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

        ItemStack newOutput = currentRecipe.getBasicFactoryData().getOutput().createStack();

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

        if (newOutput.isEmpty()) {
            return false;
        }

        ItemStack existingOutput = inventory.getStackInSlot(OUTPUT_SLOT);

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
                inventory.getSlotLimit(OUTPUT_SLOT)
        );

        return existingOutput.getCount() + newOutput.getCount() <= maxStackSize;
    }

    private void insertOutput(ItemStack newOutput) {

        ItemStack existingOutput = inventory.getStackInSlot(OUTPUT_SLOT);

        if (existingOutput.isEmpty()) {
            inventory.setStackInSlot(OUTPUT_SLOT, newOutput);
            return;
        }

        existingOutput.grow(newOutput.getCount());
    }
}