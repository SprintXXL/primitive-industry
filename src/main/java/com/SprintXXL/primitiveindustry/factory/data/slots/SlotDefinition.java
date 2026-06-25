package com.SprintXXL.primitiveindustry.factory.data.slots;

public class SlotDefinition {

    private final SlotType type;

    public SlotDefinition(
            SlotType type
    ) {
        this.type = type;
    }

    public SlotType getType() {
        return type;
    }

    public static SlotDefinition slot(SlotType type) {
        return new SlotDefinition(type);
    }

    public static SlotDefinition[] inputSlots(SlotDefinition... definition) {
        return definition;
    }

    public static SlotDefinition[] outputSlots(SlotDefinition... definition) {
        return definition;
    }
}
