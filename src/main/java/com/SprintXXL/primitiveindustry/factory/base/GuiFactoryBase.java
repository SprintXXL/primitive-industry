package com.SprintXXL.primitiveindustry.factory.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import static com.SprintXXL.primitiveindustry.Reference.MODID;

public class GuiFactoryBase extends GuiContainer {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MODID, "textures/gui/coke_oven.png");

    public GuiFactoryBase(InventoryPlayer playerInventory, TileEntityFactoryBase tile) {
        super(new ContainerFactoryBase(
                playerInventory,
                tile.getInventory(),
                tile.getFactory()
        ));

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        mc.getTextureManager().bindTexture(TEXTURE);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
