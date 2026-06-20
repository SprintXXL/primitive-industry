package com.SprintXXL.primitiveindustry.factory;

import com.SprintXXL.primitiveindustry.factory.data.gui.GuiData;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotData;
import com.SprintXXL.primitiveindustry.factory.data.structure.StructureData;

public class Factory {

    private final String id;
    private final StructureData structureData;
    private final GuiData guiData;
    private final SlotData[] slotData;

    public Factory(
            String id,
            StructureData structureData,
            GuiData guiData,
            SlotData[] slotData
    ) {
        this.id = id;
        this.structureData = structureData;
        this.guiData = guiData;
        this.slotData = slotData;
    }

    public String getID() {
        return id;
    }

    public StructureData getStructureData() {
        return structureData;
    }

    public GuiData getGuiData() {
        return guiData;
    }

    public SlotData[] getSlotData() {
        return slotData;
    }

    public String getControllerName() {
        return id + "_controller";
    }
}
