package com.SprintXXL.primitiveindustry.factory.definitions;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.data.gui.GuiData;
import com.SprintXXL.primitiveindustry.factory.data.gui.ProgressType;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotData;
import com.SprintXXL.primitiveindustry.factory.data.structure.StructureData;

import static com.SprintXXL.primitiveindustry.factory.data.gui.GuiData.gui;
import static com.SprintXXL.primitiveindustry.factory.data.gui.ProgressType.FLAME;
import static com.SprintXXL.primitiveindustry.factory.data.slots.SlotData.slots;
import static com.SprintXXL.primitiveindustry.factory.data.structure.StructureData.structure;
import static com.SprintXXL.primitiveindustry.factory.data.structure.StructureType.MULTIBLOCK;
import static com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry.register;
import static com.SprintXXL.primitiveindustry.factory.data.slots.SlotData.slot;
import static com.SprintXXL.primitiveindustry.factory.data.slots.SlotType.*;

public class ModFactories {

    private ModFactories(){}

    private static boolean initialized = false;

    public static void initFactoryDefinitions() {

        if (initialized) {
            return;
        }

        initialized = true;

        register(COKE_OVEN);
    }

    public static final Factory COKE_OVEN =
            new Factory(
                    FactoryIDs.COKE_OVEN,
                    structure(MULTIBLOCK),
                    gui(FLAME),
                    slots(
                            slot(INPUT, 55, 34),
                            slot(LARGE_OUTPUT, 103, 30)
                    )
            );
}
