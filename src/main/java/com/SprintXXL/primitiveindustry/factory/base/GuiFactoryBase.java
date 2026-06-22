package com.SprintXXL.primitiveindustry.factory.base;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import static com.SprintXXL.primitiveindustry.Reference.MODID;

public class GuiFactoryBase extends GuiContainer {

    private final TileEntityFactoryBase tile;

    public GuiFactoryBase(InventoryPlayer playerInventory, TileEntityFactoryBase tile) {
        super(new ContainerFactoryBase(
                playerInventory,
                tile.getInventory(),
                tile.getFactory()
        ));

        this.tile = tile;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/gui/" + tile.getFactory().getID() + ".png");

        mc.getTextureManager().bindTexture(TEXTURE);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        String title = tile.getFactory().getDisplayName();

        int centeredX = (170 - (fontRenderer.getStringWidth(title))) / 2;

        fontRenderer.drawString(
                title,
                centeredX,
                6,
                4210752
        );
    }
}
