package com.SprintXXL.primitiveindustry.factory.definitions;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.data.gui.GuiData;
import com.SprintXXL.primitiveindustry.factory.data.gui.slots.GuiSlotData;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotData;
import com.SprintXXL.primitiverecipeapi.factory.FactoryIDs;

import static com.SprintXXL.primitiveindustry.factory.data.gui.icons.GuiIconData.icons;
import static com.SprintXXL.primitiveindustry.factory.data.gui.icons.GuiIconDefinition.icon;
import static com.SprintXXL.primitiveindustry.factory.data.gui.icons.ProgressIcon.FLAME;
import static com.SprintXXL.primitiveindustry.factory.data.gui.slots.GuiSlotDefinition.*;
import static com.SprintXXL.primitiveindustry.factory.data.slots.SlotDefinition.*;
import static com.SprintXXL.primitiveindustry.factory.data.slots.SlotType.INPUT;
import static com.SprintXXL.primitiveindustry.factory.data.slots.SlotType.OUTPUT;
import static com.SprintXXL.primitiveindustry.factory.data.structure.StructureType.MULTIBLOCK;
import static com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry.register;

public final class ModFactories {

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
                    MULTIBLOCK,
                    new SlotData (
                            inputSlots(
                                    slot(INPUT) // 1
                            ),
                            outputSlots(
                                    slot(OUTPUT) // 1
                            )
                    ),
                    new GuiData(
                            "#807878", // Theme Color
                            "#ffffff", // Text Color
                            icons(
                                    icon(FLAME, 81, 36, 176, 0)
                            ),
                            new GuiSlotData(
                                    guiInputSlots(
                                            guiSlot(55, 34) // 1
                                    ),
                                    guiOutputSlots(
                                            largeGuiSlot(103, 30) // 1
                                    )
                            )
                    )
            );
}
