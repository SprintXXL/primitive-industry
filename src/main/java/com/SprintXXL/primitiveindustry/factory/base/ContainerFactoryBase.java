package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotData;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFactoryBase extends Container {

    private final TileEntityFactoryBase tile;

    public ContainerFactoryBase(
            InventoryPlayer playerInventory,
            IItemHandler factoryInventory,
            Factory factory,
            TileEntityFactoryBase tile
    ) {

        this.tile = tile;

        addFactorySlots(factoryInventory, factory.getSlotData());
        addPlayerInventory(playerInventory);
    }

    private void addFactorySlots(IItemHandler inventory, SlotData[] slots) {

        for (int i = 0; i < slots.length; i++) {
            SlotData slot = slots[i];

            if (slot.getType() == SlotType.OUTPUT) {
                addSlotToContainer(
                        new SlotItemHandler(
                                inventory,
                                i,
                                slot.getX() + slot.getContainerOffsetX(),
                                slot.getY() + slot.getContainerOffsetY()
                        ) {
                            @Override
                            public boolean isItemValid(ItemStack stack) {
                                return false;
                            }
                        }
                );
            } else {
                addSlotToContainer(
                        new SlotItemHandler(
                                inventory,
                                i,
                                slot.getX() + slot.getContainerOffsetX(),
                                slot.getY() + slot.getContainerOffsetY()
                        )
                );
            }
        }
    }

    private void addPlayerInventory(InventoryPlayer playerInventory) {

        int startX = 8;
        int startY = 84;

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

        int hotbarY = 142;

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

        int factorySlotCount = tile.getFactory().getSlotData().length;
        int playerStart = factorySlotCount;
        int playerEnd = inventorySlots.size();

        int inputStart = tile.getFactory().getInputSlots().get(0);
        int inputEnd = inputStart + 1;

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
