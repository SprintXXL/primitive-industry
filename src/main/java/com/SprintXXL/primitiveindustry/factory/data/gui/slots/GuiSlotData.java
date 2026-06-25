package com.SprintXXL.primitiveindustry.factory.data.gui.slots;

public class GuiSlotData {

    private final GuiSlotDefinition[] guiInputSlots;
    private final GuiSlotDefinition[] guiOutputSlots;
    private final GuiSlotDefinition[] allGuiSlots;

    public GuiSlotData(
            GuiSlotDefinition[] guiInputSlots,
            GuiSlotDefinition[] guiOutputSlots
    ) {
        this.guiInputSlots = guiInputSlots;
        this.guiOutputSlots = guiOutputSlots;
        this.allGuiSlots = mergeSlots(guiInputSlots, guiOutputSlots);
    }

    public GuiSlotDefinition[] getGuiInputSlots() {
        return guiInputSlots;
    }

    public GuiSlotDefinition[] getGuiOutputSlots() {
        return guiOutputSlots;
    }

    public GuiSlotDefinition[] getAllGuiSlots() {
        return allGuiSlots;
    }

    private static GuiSlotDefinition[] mergeSlots(
            GuiSlotDefinition[] guiInputSlots,
            GuiSlotDefinition[] guiOutputSlots
    ) {

        GuiSlotDefinition[] result = new GuiSlotDefinition[guiInputSlots.length + guiOutputSlots.length];

        System.arraycopy(guiInputSlots, 0, result, 0, guiInputSlots.length);
        System.arraycopy(guiOutputSlots, 0, result, guiInputSlots.length, guiOutputSlots.length);

        return result;
    }
}
