package com.SprintXXL.primitiveindustry.factory.data.slots;

public class SlotData {

    private final SlotType type;
    private final int x;
    private final int y;

    public SlotData(
            SlotType type,
            int x,
            int y
    ) {
        this.type = type;
        this.x = x;
        this.y = y;
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

    public static SlotData slot(SlotType type, int x, int y) {
        return new SlotData(type, x, y);
    }

    public static SlotData[] slots(SlotData... data) {
        return data;
    }
}
