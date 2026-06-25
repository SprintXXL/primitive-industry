package com.SprintXXL.primitiveindustry.factory.data.gui;

import com.SprintXXL.primitiveindustry.factory.data.gui.icons.GuiIconData;
import com.SprintXXL.primitiveindustry.factory.data.gui.slots.GuiSlotData;

public class GuiData {

    private final String guiThemeColor;
    private final String guiTextColor;
    private final GuiIconData guiIconData;
    private final GuiSlotData guiSlotData;

    public GuiData(
            String guiThemeColor,
            String guiTextColor,
            GuiIconData guiIconData,
            GuiSlotData guiSlotData
    ) {
        this.guiThemeColor = guiThemeColor;
        this.guiTextColor = guiTextColor;
        this.guiIconData = guiIconData;
        this.guiSlotData = guiSlotData;
    }

    public String getGuiThemeColor() {
        return guiThemeColor;
    }

    public String getGuiTextColor() {
        return guiTextColor;
    }

    public GuiIconData getGuiIconData() {
        return guiIconData;
    }

    public GuiSlotData getGuiSlotData() {
        return guiSlotData;
    }
}
