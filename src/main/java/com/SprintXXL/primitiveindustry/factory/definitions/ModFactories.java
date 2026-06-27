package com.SprintXXL.primitiveindustry.factory.definitions;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.data.gui.GuiData;
import com.SprintXXL.primitiveindustry.factory.data.gui.slots.GuiSlotData;
import com.SprintXXL.primitiveindustry.factory.data.slots.SlotData;
import com.SprintXXL.primitiverecipeapi.factory.FactoryIDs;

import static com.SprintXXL.primitiveindustry.factory.data.gui.icons.GuiIconData.icons;
import static com.SprintXXL.primitiveindustry.factory.data.gui.icons.GuiIconDefinition.icon;
import static com.SprintXXL.primitiveindustry.factory.data.gui.icons.ProgressIcon.ARROW;
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

        // TEST \\
        register(TEST_FACTORY);

        // FACTORIES \\
        register(COKE_OVEN);
    }

    // TEST \\
    public static final Factory TEST_FACTORY =
            new Factory(
                    FactoryIDs.TEST_FACTORY,
                    MULTIBLOCK,
                    new SlotData(
                            inputSlots(
                                    slot(INPUT), // 1
                                    slot(INPUT), // 2
                                    slot(INPUT), // 3
                                    slot(INPUT), // 4
                                    slot(INPUT), // 5
                                    slot(INPUT)  // 6
                            ),
                            outputSlots(
                                    slot(OUTPUT), // 1
                                    slot(OUTPUT), // 2
                                    slot(OUTPUT), // 3
                                    slot(OUTPUT), // 4
                                    slot(OUTPUT), // 5
                                    slot(OUTPUT)  // 6
                            )
                    ),
                    new GuiData(
                            "#70401e",
                            "#ffffff",
                            icons(
                                    icon(ARROW, 115, 80, 256, 0),
                                    icon(FLAME, 76, 53, 256, 30),
                                    icon(FLAME, 164, 53, 256, 60)
                            ),
                            new GuiSlotData(
                                    guiInputSlots(
                                            guiSlot(56, 70), // 1
                                            guiSlot(74, 70), // 2
                                            guiSlot(92, 70), // 3
                                            guiSlot(56, 88), // 4
                                            guiSlot(74, 88), // 5
                                            guiSlot(92, 88)  // 6
                                    ),

                                    guiOutputSlots(
                                            guiSlot(144, 70), // 1
                                            guiSlot(162, 70), // 2
                                            guiSlot(180, 70), // 3
                                            guiSlot(144, 88), // 4
                                            guiSlot(162, 88), // 5
                                            guiSlot(180, 88)  // 6
                                    )
                            )
                    )
            );

    // FACTORIES \\
    public static final Factory COKE_OVEN =
            new Factory(
                    FactoryIDs.COKE_OVEN,
                    MULTIBLOCK,
                    new SlotData(
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
                                    icon(FLAME, 121, 81, 256, 0)
                            ),
                            new GuiSlotData(
                                    guiInputSlots(
                                            guiSlot(95, 79) // 1
                                    ),
                                    guiOutputSlots(
                                            largeGuiSlot(143, 75) // 1
                                    )
                            )
                    )
            );
}
