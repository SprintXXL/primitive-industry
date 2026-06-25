package com.SprintXXL.primitiveindustry.factory.data.slots;

public class SlotData {

    private final SlotDefinition[] inputSlots;
    private final SlotDefinition[] outputSlots;
    private final SlotDefinition[] allSlots;

    public SlotData(
            SlotDefinition[] inputSlots,
            SlotDefinition[] outputSlots
    ) {
        this.inputSlots = inputSlots;
        this.outputSlots = outputSlots;
        this.allSlots = mergeSlots(inputSlots, outputSlots);
    }

    public SlotDefinition[] getInputSlots() {
        return inputSlots;
    }

    public SlotDefinition[] getOutputSlots() {
        return outputSlots;
    }

    public SlotDefinition[] getAllSlots() {
        return allSlots;
    }

    private static SlotDefinition[] mergeSlots(
            SlotDefinition[] inputSlots,
            SlotDefinition[] outputSlots
    ) {

        SlotDefinition[] result = new SlotDefinition[inputSlots.length + outputSlots.length];

        System.arraycopy(inputSlots, 0, result, 0, inputSlots.length);
        System.arraycopy(outputSlots, 0, result, inputSlots.length, outputSlots.length);

        return result;
    }
}
