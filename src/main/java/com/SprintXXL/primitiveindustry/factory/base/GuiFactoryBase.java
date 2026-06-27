package com.SprintXXL.primitiveindustry.factory.base;

import com.SprintXXL.primitiveindustry.factory.data.gui.icons.GuiIconDefinition;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import static com.SprintXXL.primitiveindustry.Reference.MODID;

public class GuiFactoryBase extends GuiContainer {

    private final TileEntityFactoryBase tile;

    private static final int TEXTURE_WIDTH = 288;
    private static final int TEXTURE_HEIGHT = 256;

    public GuiFactoryBase(InventoryPlayer playerInventory, TileEntityFactoryBase tile) {
        super(new ContainerFactoryBase(
                playerInventory,
                tile.getInventory(),
                tile.getFactory(),
                tile
        ));

        this.tile = tile;

        this.xSize = 256;
        this.ySize = 256;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        ResourceLocation TEXTURE = new ResourceLocation(MODID, "textures/gui/" + tile.getFactory().getID() + ".png");

        mc.getTextureManager().bindTexture(TEXTURE);

        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;

        drawModalRectWithCustomSizedTexture(x, y, 0, 0, xSize, ySize, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        if (tile.getMaxProgress() <= 0) {
            return;
        }

        int progress = tile.getProgress();
        int maxProgress = tile.getMaxProgress();

        for (GuiIconDefinition icon : tile.getFactory().getGuiData().getGuiIconData().getGuiIcons()) {
            renderProgressIcon(icon, progress, maxProgress);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        this.drawDefaultBackground();

        super.drawScreen(mouseX, mouseY, partialTicks);

        this.renderHoveredToolTip(mouseX, mouseY);
    }

    private void renderProgressIcon(GuiIconDefinition icon, int progress, int maxProgress) {

        switch (icon.getIcon()) {

            case ARROW:
                renderHorizontalProgress(icon, progress, maxProgress);
                break;

            case FLAME:
                renderVerticalProgress(icon, progress, maxProgress);
                break;
        }
    }

    private void renderHorizontalProgress(GuiIconDefinition icon, int progress, int maxProgress) {

        if (maxProgress <= 0) {
            return;
        }

        int iconWidth = icon.getWidth();
        int progressWidth = progress * iconWidth / maxProgress;

        if (progressWidth < 2) {
            return;
        }

        drawModalRectWithCustomSizedTexture(
                guiLeft + icon.getVisibleX(),
                guiTop + icon.getVisibleY(),

                icon.getHiddenX(),
                icon.getHiddenY(),

                progressWidth,
                icon.getHeight(),

                TEXTURE_WIDTH,
                TEXTURE_HEIGHT
        );
    }

    private void renderVerticalProgress(GuiIconDefinition icon, int progress, int maxProgress) {

        if (maxProgress <= 0) {
            return;
        }

        int iconHeight = icon.getHeight();
        int progressHeight = progress * iconHeight / maxProgress;

        if (progressHeight < 2) {
            return;
        }

        drawModalRectWithCustomSizedTexture(
                guiLeft + icon.getVisibleX(),
                guiTop + icon.getVisibleY() + (iconHeight - progressHeight),

                icon.getHiddenX(),
                icon.getHiddenY() + (iconHeight - progressHeight),

                icon.getWidth(),
                progressHeight,

                TEXTURE_WIDTH,
                TEXTURE_HEIGHT
        );
    }
}
