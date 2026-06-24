package com.SprintXXL.primitiveindustry.factory;

import com.SprintXXL.primitiveindustry.factory.data.gui.GuiData;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotData;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotType;
import com.SprintXXL.primitiveindustry.factory.data.structure.StructureType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factory {

    private final String id;
    private final StructureType structureType;
    private final GuiData guiData;
    private final SlotData[] slotData;

    public Factory(
            String id,
            StructureType structureType,
            GuiData guiData,
            SlotData[] slotData
    ) {
        this.id = id;
        this.structureType = structureType;
        this.guiData = guiData;
        this.slotData = slotData;
    }

    public String getID() {
        return id;
    }

    public StructureType getStructureType() {
        return structureType;
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

    public String getDisplayName() {

        String[] words = id.split("_");

        StringBuilder result = new StringBuilder();

        for (String word : words) {

            result.append(Character.toUpperCase(word.charAt(0)));
            result.append(word.substring(1));
            result.append(" ");
        }

        return result.toString().trim();
    }

    public List<Integer> getInputSlots() {
        return getSlotsOfType(SlotType.INPUT);
    }

    public List<Integer> getOutputSlots() {
        return getSlotsOfType(SlotType.OUTPUT);
    }

    private List<Integer> getSlotsOfType(SlotType type) {

        List<Integer> slots = new ArrayList<>();

        for (int i = 0; i < slotData.length; i++) {
            if (slotData[i].getType() == type) {
                slots.add(i);
            }
        }

        return Collections.unmodifiableList(slots);
    }
}
