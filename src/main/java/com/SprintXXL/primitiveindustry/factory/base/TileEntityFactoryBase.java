package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.recipe.FactoryRecipe;
import com.SprintXXL.primitiveindustry.factory.recipe.FactoryRecipeMatcher;
import com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry;
import com.sprintxxl.ascentresourcerecipeindex.recipes.AscentRecipe;
import com.sprintxxl.ascentresourcerecipeindex.recipes.api.RecipeResourceMatcher;
import com.sprintxxl.ascentresourcerecipeindex.recipes.api.RecipeResourceResolver;
import com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.RecipeResource;
import com.sprintxxl.ascentresourcerecipeindex.recipes.registry.AscentRecipeRegistry;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

import static com.sprintxxl.ascenthub.framework.gui.slots.SlotType.INPUT;
import static com.sprintxxl.ascenthub.framework.gui.slots.SlotType.OUTPUT;

public class TileEntityFactoryBase extends TileEntity implements ITickable {

    private ItemStackHandler inventory;
    private String factoryID;
    private FactoryRecipe currentRecipe;
    private ResourceLocation currentRecipeID;

    private int progress = 0;
    private int maxProgress = 0;

    public TileEntityFactoryBase() {
        this.inventory = new ItemStackHandler(0);
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

        int slotCount = factory.getGui().getSlotData().getSlotCount();

        if (inventory.getSlots() != slotCount) {
            this.inventory = new ItemStackHandler(slotCount);
        }
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
    public boolean shouldRefresh(
            World world,
            BlockPos pos,
            IBlockState oldState,
            IBlockState newState
    ) {
        return oldState.getBlock() != newState.getBlock();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setTag("Inventory", inventory.serializeNBT());

        if (factoryID != null) {
            compound.setString("FactoryID", factoryID);
        }

        if (currentRecipeID != null) {
            compound.setString("CurrentRecipeID", currentRecipeID.toString());
        }

        compound.setInteger("Progress", progress);
        compound.setInteger("MaxProgress", maxProgress);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.hasKey("FactoryID")) {
            factoryID = compound.getString("FactoryID");
        }

        Factory factory = getFactory();

        if (factory != null) {

            int slotCount = factory.getGui().getSlotData().getSlotCount();

            if (inventory.getSlots() != slotCount) {
                this.inventory = new ItemStackHandler(slotCount);
            }
        }

        if (compound.hasKey("Inventory")) {
            inventory.deserializeNBT(compound.getCompoundTag("Inventory"));
        }

        String savedRecipeID = compound.getString("CurrentRecipeID");

        if (!savedRecipeID.isEmpty()) {
            currentRecipeID = new ResourceLocation(savedRecipeID);
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

        List<Integer> inputSlots = factory.getGui().getSlotData().getSlotIndices(INPUT);
        List<Integer> outputSlots = factory.getGui().getSlotData().getSlotIndices(OUTPUT);

        if (inputSlots.isEmpty() || outputSlots.isEmpty()) {
            return;
        }

        if (currentRecipe == null) {
            if (!tryStartRecipe(factory)) {
                progress = 0;
                maxProgress = 0;
                updateActiveBlockState();
                return;
            }

            updateActiveBlockState();
        }

        if (progress < maxProgress) {
            progress++;
        }

        if (progress < maxProgress) {
            return;
        }


        if (!canFitOutputs(currentRecipe)) {
            progress = maxProgress;
            return;
        }

        insertOutputs(currentRecipe);

        currentRecipe = null;
        currentRecipeID = null;
        progress = 0;
        maxProgress = 0;

        tryStartRecipe(factory);

        updateActiveBlockState();
    }

    private boolean canFitOutputs(FactoryRecipe recipe) {

        for (int i = 0; i < recipe.getOutputs().size(); i++) {
            RecipeResource output = recipe.getOutputs().get(i);
            ItemStack stack = RecipeResourceResolver.createStack(output);

            if (!canFitOutput(stack, output, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean canFitOutput(ItemStack newOutput, RecipeResource output, int outputIndex) {

        Factory factory = getFactory();

        if (factory == null) {
            return false;
        }

        int outputSlot = getOutputSlotFor(factory, output, outputIndex);

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

    private void insertOutputs(FactoryRecipe recipe) {

        for (int i = 0; i < recipe.getOutputs().size(); i++) {
            RecipeResource output = recipe.getOutputs().get(i);
            ItemStack stack = RecipeResourceResolver.createStack(output);

            insertOutput(stack, output, i);
        }
    }

    private void insertOutput(ItemStack newOutput, RecipeResource output, int outputIndex) {

        Factory factory = getFactory();

        if (factory == null) {
            return;
        }

        int outputSlot = getOutputSlotFor(factory, output, outputIndex);

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

        for (int slot : factory.getGui().getSlotData().getSlotIndices(INPUT)) {
            inputStacks.add(inventory.getStackInSlot(slot));
        }

        return inputStacks;
    }

    private void consumeInputs(Factory factory, FactoryRecipe recipe) {

        switch (recipe.getMatchingMode()) {

            case POSITIONAL:
                consumePositionalInputs(factory, recipe);
                break;

            case POOLED:
                consumePooledInputs(factory, recipe);
                break;
        }
    }

    private void consumePositionalInputs(Factory factory, FactoryRecipe recipe) {

        for (int i = 0; i < recipe.getInputs().size(); i++) {

            int slot = getInputSlotFor(factory, recipe.getInputs().get(i), i);

            int count = recipe.getInputs().get(i).getCount();

            inventory.getStackInSlot(slot).shrink(count);
        }
    }

    private void consumePooledInputs(Factory factory, FactoryRecipe recipe) {

        List<Integer> inputSlots = factory.getGui().getSlotData().getSlotIndices(INPUT);

        for (RecipeResource input : recipe.getInputs()) {

            int remaining = input.getCount();

            if (input.hasInputSlotOverride()) {

                int slot = getInputSlotFor(factory, input, 0);

                if (slot < 0) {
                    continue;
                }

                ItemStack stack = inventory.getStackInSlot(slot);

                if (RecipeResourceMatcher.matches(input, stack)) {
                    stack.shrink(remaining);
                }

                continue;
            }

            for (int slot : inputSlots) {

                ItemStack stack = inventory.getStackInSlot(slot);

                if (!RecipeResourceMatcher.matches(input, stack)) {
                    continue;
                }

                int consumed = Math.min(stack.getCount(), remaining);

                stack.shrink(consumed);
                remaining -= consumed;

                if (remaining <= 0) {
                    break;
                }
            }
        }
    }

    private void restoreCurrentRecipe() {

        if (currentRecipe != null || currentRecipeID == null) {
            return;
        }

        AscentRecipe recipe = AscentRecipeRegistry.getRecipe(currentRecipeID);

        if (recipe instanceof FactoryRecipe) {
            currentRecipe = (FactoryRecipe) recipe;
            return;
        }

        currentRecipeID = null;
        progress = 0;
        maxProgress = 0;
    }

    private void updateActiveBlockState() {

        boolean active = isProcessing();

        IBlockState state = world.getBlockState(pos);

        if (!(state.getBlock() instanceof BlockControllerBase)) {
            return;
        }

        if (state.getValue(BlockControllerBase.ACTIVE) == active) {
            return;
        }

        world.setBlockState(
                pos,
                state.withProperty(BlockControllerBase.ACTIVE, active),
                3
        );
    }

    private boolean tryStartRecipe(Factory factory) {

        List<ItemStack> inputStacks = getInputStacks(factory);

        FactoryRecipe recipe = FactoryRecipeMatcher.findMatchingRecipe(factoryID, inputStacks);

        if (recipe == null) {
            return false;
        }

        consumeInputs(factory, recipe);

        currentRecipe = recipe;
        currentRecipeID = recipe.getID();
        maxProgress = recipe.getDurationTicks();
        progress = 0;

        return true;
    }

    private int getInputSlotFor(Factory factory, RecipeResource input, int inputIndex) {

        List<Integer> inputSlots = factory.getGui().getSlotData().getSlotIndices(INPUT);

        int factoryInputIndex = inputIndex;

        if (input.hasInputSlotOverride()) {
            factoryInputIndex = input.getInputSlotOverride() - 1;
        }

        if (factoryInputIndex < 0 || factoryInputIndex >= inputSlots.size()) {
            return -1;
        }

        return inputSlots.get(factoryInputIndex);
    }

    private int getOutputSlotFor(Factory factory, RecipeResource output, int outputIndex) {

        List<Integer> outputSlots = factory.getGui().getSlotData().getSlotIndices(OUTPUT);

        int factoryOutputIndex = outputIndex;

        if (output.hasOutputSlotOverride()) {
            factoryOutputIndex = output.getOutputSlotOverride() - 1;
        }

        if (factoryOutputIndex < 0 || factoryOutputIndex >= outputSlots.size()) {
            return -1;
        }

        return outputSlots.get(factoryOutputIndex);
    }
}