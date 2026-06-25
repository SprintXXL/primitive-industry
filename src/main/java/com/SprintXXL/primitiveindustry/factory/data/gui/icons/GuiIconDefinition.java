package com.SprintXXL.primitiveindustry.factory.data.gui.icons;

public class GuiIconDefinition {

    private final ProgressIcon icon;
    private final int visibleX;
    private final int visibleY;
    private final int hiddenX;
    private final int hiddenY;

    public GuiIconDefinition(
            ProgressIcon icon,
            int visibleX,
            int visibleY,
            int hiddenX,
            int hiddenY
    ) {
        this.icon = icon;
        this.visibleX = visibleX;
        this.visibleY = visibleY;
        this.hiddenX = hiddenX;
        this.hiddenY = hiddenY;
    }

    public ProgressIcon getIcon() {
        return icon;
    }

    public int getVisibleX() {
        return visibleX;
    }

    public int getVisibleY() {
        return visibleY;
    }

    public int getHiddenX() {
        return hiddenX;
    }

    public int getHiddenY() {
        return hiddenY;
    }


    public static GuiIconDefinition icon(ProgressIcon icon, int visibleX, int visibleY, int hiddenX, int hiddenY) {
        return new GuiIconDefinition(icon, visibleX, visibleY, hiddenX, hiddenY);
    }
}
