package com.SprintXXL.primitiveindustry.factory.data.structure;

public class StructureData {

    private final StructureType type;

    public StructureData(
            StructureType type
    ) {
        this.type = type;
    }

    public StructureType getType() {
        return type;
    }

    public static StructureData structure(StructureType type) {
        return new StructureData(type);
    }
}
