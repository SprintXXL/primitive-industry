package com.SprintXXL.primitiveindustry.factory;

import com.SprintXXL.primitiveindustry.factory.data.structure.StructureType;
import com.sprintxxl.ascenthub.definitions.AscentDefinition;
import com.sprintxxl.ascenthub.framework.gui.AscentGUI;

public class Factory implements AscentDefinition {

    private final String id;
    private final StructureType structureType;
    private final AscentGUI gui;

    public Factory(
            String id,
            StructureType structureType,
            AscentGUI gui
    ) {
        this.id = id;
        this.structureType = structureType;
        this.gui = gui;
    }

    @Override
    public String getID() {
        return id;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public AscentGUI getGui() {
        return gui;
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
}
