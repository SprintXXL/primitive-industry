package com.SprintXXL.primitiveindustry.factory.data.slots;

public class SlotData {

    private final SlotType type;
    private final int x;
    private final int y;
    private final int containerOffsetX;
    private final int containerOffsetY;

    public SlotData(
            SlotType type,
            int x,
            int y,
            int containerOffsetX,
            int containerOffsetY
    ) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.containerOffsetX = containerOffsetX;
        this.containerOffsetY = containerOffsetY;
    }

    public SlotType getType() {
        return type;
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

    public static SlotData slot(SlotType type, int x, int y) {
        return new SlotData(type, x, y, 1, 1);
    }

    public static SlotData largeSlot(SlotType type, int x, int y) {
        return new SlotData(type, x, y, 5, 5);
    }

    public static SlotData[] slots(SlotData... data) {
        return data;
    }
}
