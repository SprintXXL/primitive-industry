package com.SprintXXL.primitiveindustry.factory.data.gui.slots;

public class GuiSlotDefinition {

    private final int x;
    private final int y;
    private final int containerOffsetX;
    private final int containerOffsetY;
    
    public GuiSlotDefinition(
            int x,
            int y,
            int containerOffsetX,
            int containerOffsetY
    ) {
        this.x = x;
        this.y = y;
        this.containerOffsetX = containerOffsetX;
        this.containerOffsetY = containerOffsetY;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getContainerOffsetX() {
        return containerOffsetX;
    }
    
    public int getContainerOffsetY() {
        return containerOffsetY;
    }

    public static GuiSlotDefinition guiSlot(int x, int y) {
        return new GuiSlotDefinition(x, y, 1, 1);
    }

    public static GuiSlotDefinition largeGuiSlot(int x, int y) {
        return new GuiSlotDefinition(x, y, 5, 5);
    }

    public static GuiSlotDefinition[] guiInputSlots(GuiSlotDefinition... definition) {
        return definition;
    }

    public static GuiSlotDefinition[] guiOutputSlots(GuiSlotDefinition... definition) {
        return definition;
    }
}
