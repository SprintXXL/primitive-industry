package com.SprintXXL.primitiveindustry.factory.data.gui.icons;

public class GuiIconData {

    private final GuiIconDefinition[] guiIcons;

    public GuiIconData(
            GuiIconDefinition[] guiIcons
    ) {
        this.guiIcons = guiIcons;
    }

    public GuiIconDefinition[] getGuiIcons() {
        return guiIcons;
    }

    public static GuiIconData icons(GuiIconDefinition... icons) {
        return new GuiIconData(icons);
    }
}
