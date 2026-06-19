package com.SprintXXL.primitiveindustry.factory;

import com.SprintXXL.primitiveindustry.factory.data.SlotData;

public class Factory {

    private final String id;
    private final boolean requiresMultiblock;
    private final String multiblockID;
    private final SlotData[] slotData;

    public Factory(
            String id,
            boolean requiresMultiblock,
            String multiblockID,
            SlotData[] slotData
    ) {
        this.id = id;
        this.requiresMultiblock = requiresMultiblock;
        this.multiblockID = multiblockID;
        this.slotData = slotData;
    }

    public String getID() {
        return id;
    }

    public boolean requiresMultiblock() {
        return requiresMultiblock;
    }

    public String getMultiblockID() {
        return multiblockID;
    }

    public SlotData[] getSlotData() {
        return slotData;
    }

    public String getControllerName() {
        return id + "_controller";
    }
}
