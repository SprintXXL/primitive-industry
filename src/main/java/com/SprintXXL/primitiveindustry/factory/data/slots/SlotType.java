package com.SprintXXL.primitiveindustry.factory.data.slots;

public enum SlotType {

    INPUT(1, 1),
    OUTPUT(1, 1),
    LARGE_OUTPUT(5, 5);

    private final int containerOffsetX;
    private final int containerOffsetY;

    SlotType(
            int containerOffsetX,
            int containerOffsetY
    ) {
        this.containerOffsetX = containerOffsetX;
        this.containerOffsetY = containerOffsetY;
    }

    public int getContainerOffsetX() {
        return containerOffsetX;
    }

    public int getContainerOffsetY() {
        return containerOffsetY;
    }
}
