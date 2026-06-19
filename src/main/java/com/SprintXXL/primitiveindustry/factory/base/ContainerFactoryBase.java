package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.data.SlotData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerFactoryBase extends Container {

    public ContainerFactoryBase(
            InventoryPlayer playerInventory,
            IItemHandler factoryInventory,
            Factory factory
    ) {
        addFactorySlots(factoryInventory, factory.getSlotData());
        addPlayerInventory(playerInventory);
    }

    private void addFactorySlots(IItemHandler inventory, SlotData[] slots) {

        for (int i = 0; i < slots.length; i++) {
            SlotData slot = slots[i];

            addSlotToContainer(
                    new SlotItemHandler(
                            inventory,
                            i,
                            slot.getX(),
                            slot.getY()
                    )
            );
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
}
