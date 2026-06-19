package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.factory.Factory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityFactoryBase extends TileEntity {

    private final ItemStackHandler inventory;
    private Factory factory;

    public TileEntityFactoryBase() {
        this.inventory = new ItemStackHandler(2);
    }

    public IItemHandler getInventory() {
        return inventory;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
