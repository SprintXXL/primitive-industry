package com.SprintXXL.primitiveindustry.factory.data.gui.icons;

public enum ProgressIcon {

    ARROW(27, 27),
    FLAME(14, 14);

    private final int width;
    private final int height;

    ProgressIcon(
            int width,
            int height
    ) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
