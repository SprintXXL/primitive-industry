package com.SprintXXL.primitiveindustry.factory.data;

public class SlotData {

    private final String id;
    private final int x;
    private final int y;

    public SlotData(
            String id,
            int x,
            int y
    ) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getID() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
