package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.sprintxxl.ascenthub.framework.gui.slots.SlotDefinition;
import com.sprintxxl.ascenthub.framework.gui.slots.SlotGroup;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.List;

import static com.sprintxxl.ascenthub.framework.gui.slots.SlotType.INPUT;
import static com.sprintxxl.ascenthub.framework.gui.slots.SlotType.OUTPUT;

public class ContainerFactoryBase extends Container {

    private final TileEntityFactoryBase tile;

    public ContainerFactoryBase(
            InventoryPlayer playerInventory,
            IItemHandler factoryInventory,
            Factory factory,
            TileEntityFactoryBase tile
    ) {

        this.tile = tile;

        addFactorySlots(factoryInventory, factory);
        addPlayerInventory(playerInventory);
    }

    private void addFactorySlots(IItemHandler inventory, Factory factory) {

        int inventoryIndex = 0;

        for (SlotGroup group : factory.getGui().getSlotData().getSlotGroups()) {

            for (SlotDefinition slot : group.getSlots()) {

                if (group.getType() == OUTPUT) {
                    addOutputSlot(inventory, inventoryIndex, slot);
                } else {
                    addNormalSlot(inventory, inventoryIndex, slot);
                }

                inventoryIndex++;
            }
        }
    }

    private void addOutputSlot(IItemHandler inventory, int inventoryIndex, SlotDefinition slot) {
        addSlotToContainer(new SlotItemHandler(
                inventory,
                inventoryIndex,
                slot.getContainerX(),
                slot.getContainerY()
        ) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return false;
            }
        }
        );
    }

    private void addNormalSlot(IItemHandler inventory, int inventoryIndex, SlotDefinition slot) {
        addSlotToContainer(new SlotItemHandler(
                inventory,
                inventoryIndex,
                slot.getContainerX(),
                slot.getContainerY()
        ));
    }

    private void addPlayerInventory(InventoryPlayer playerInventory) {

        int startX = 48;
        int startY = 129;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(
                        new Slot(
                                playerInventory,
                                col + row * 9 + 9,
                                startX + col * 18,
                                startY + row * 18
                        )
                );
            }
        }

        int hotbarY = 187;

        for (int col = 0; col < 9; col++) {
            addSlotToContainer(
                    new Slot(
                            playerInventory,
                            col,
                            startX + col * 18,
                            hotbarY
                    )
            );
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener listener : listeners) {
            listener.sendWindowProperty(this, 0, tile.getProgress());
            listener.sendWindowProperty(this, 1, tile.getMaxProgress());
        }
    }

    @Override
    public void updateProgressBar(int id, int data) {

        if (id == 0) {
            tile.setProgress(data);
        }

        if (id == 1) {
            tile.setMaxProgress(data);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {

        ItemStack originalStack;
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack stackInSlot = slot.getStack();
        originalStack = stackInSlot.copy();

        int factorySlotCount = tile.getFactory().getGui().getSlotData().getSlotCount();
        int playerStart = factorySlotCount;
        int playerEnd = inventorySlots.size();

        List<Integer> inputs = tile.getFactory().getGui().getSlotData().getSlotIndices(INPUT);

        if (inputs.isEmpty()) {
            return ItemStack.EMPTY;
        }

        int inputStart = inputs.get(0);
        int inputEnd = inputStart + inputs.size();

        if (index < factorySlotCount) {
            if (!mergeItemStack(stackInSlot, playerStart, playerEnd, true)) {
                return ItemStack.EMPTY;
            }

        } else {
            if (!mergeItemStack(stackInSlot, inputStart, inputEnd, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stackInSlot.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
        }
        else {
            slot.onSlotChanged();
        }
        if (stackInSlot.getCount() == originalStack.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(playerIn, stackInSlot);

        return originalStack;
    }
}
