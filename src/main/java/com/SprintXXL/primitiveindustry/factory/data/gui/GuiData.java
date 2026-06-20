package com.SprintXXL.primitiveindustry.factory.data.gui;

public class GuiData {

    private final ProgressType type;

    public GuiData(
            ProgressType type
    ) {
        this.type = type;
    }

    public ProgressType getType() {
        return type;
    }

    public static GuiData gui(ProgressType type) {
        return new GuiData(type);
    }
}
