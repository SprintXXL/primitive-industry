package com.SprintXXL.primitiveindustry.client;

import com.SprintXXL.primitiveindustry.PrimitiveIndustry;
import com.SprintXXL.primitiveindustry.factory.base.GuiFactoryBase;
import com.SprintXXL.primitiveindustry.factory.base.ContainerFactoryBase;
import com.SprintXXL.primitiveindustry.factory.base.TileEntityFactoryBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(
            int ID,
            EntityPlayer player,
            World world,
            int x,
            int y,
            int z
    ) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        if (!(tile instanceof TileEntityFactoryBase)) {
            return null;
        }

        TileEntityFactoryBase factoryTile = (TileEntityFactoryBase) tile;

        if (factoryTile.getFactory() == null) {
            return null;
        }

        return new ContainerFactoryBase(
                player.inventory,
                factoryTile.getInventory(),
                factoryTile.getFactory(),
                factoryTile
        );
    }

    @Override
    public Object getClientGuiElement(
            int ID,
            EntityPlayer player,
            World world,
            int x,
            int y,
            int z
    ) {

        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

        if (!(tile instanceof TileEntityFactoryBase)) {
            return null;
        }

        TileEntityFactoryBase factoryTile = (TileEntityFactoryBase) tile;

        return new GuiFactoryBase(
                player.inventory,
                factoryTile
        );
    }
}
