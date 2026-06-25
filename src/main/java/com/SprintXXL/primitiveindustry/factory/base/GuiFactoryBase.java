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
                tile.getFactory(),
                tile
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

        drawTexturedModalRect(x, y, 40, 45, xSize, ySize);

        drawTexturedModalRect(
                x + 6,
                y - 14,
                46,
                31,
                78, // CHANGE TO GENERIC headerWidth
                14
        );

        if (tile.getMaxProgress() > 0) {
            int flameHeight = tile.getProgress() * 14 / tile.getMaxProgress();

            drawTexturedModalRect(
                    x + 81,
                    y + 36 + (14 - flameHeight),
                    216,
                    45 + (14 - flameHeight),
                    14,
                    flameHeight
            );
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);

        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
