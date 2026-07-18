package com.SprintXXL.primitiveindustry.factory.definitions;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.sprintxxl.ascenthub.definitions.DefinitionRegistrar;
import com.sprintxxl.ascenthub.framework.gui.AscentGUI;
import com.sprintxxl.ascenthub.framework.gui.cosmetic.CosmeticData;
import com.sprintxxl.ascenthub.framework.gui.icons.IconData;
import com.sprintxxl.ascenthub.framework.gui.slots.SlotData;

import static com.SprintXXL.primitiveindustry.factory.data.structure.StructureType.*;
import static com.sprintxxl.ascenthub.framework.gui.icons.IconDefinition.progressIcon;
import static com.sprintxxl.ascenthub.framework.gui.icons.Icons.*;
import static com.sprintxxl.ascenthub.framework.gui.slots.SlotDefinition.largeSlot;
import static com.sprintxxl.ascenthub.framework.gui.slots.SlotDefinition.slot;
import static com.sprintxxl.ascenthub.framework.gui.slots.SlotGroup.inputSlots;
import static com.sprintxxl.ascenthub.framework.gui.slots.SlotGroup.outputSlots;
import static com.sprintxxl.ascenthub.framework.gui.slots.slotsettings.SlotSettings.addPlayerSlots;
import static com.sprintxxl.ascenthub.framework.gui.slots.slotsettings.SlotSettings.slotSettings;

public final class ModFactories {

    private ModFactories(){}

    public static void initFactoryDefinitions(DefinitionRegistrar<Factory> registrar) {

        // TEST \\
        registrar.register(TEST_FACTORY);

        // FACTORIES \\
        registrar.register(COKE_OVEN);

    }

    // TEST \\
    public static final Factory TEST_FACTORY =
            new Factory(
                    FactoryIDs.TEST_FACTORY,
                    MULTIBLOCK,
                    new AscentGUI(
                            new CosmeticData(
                                    "#ffffff",
                                    "Test Factory"
                            ),
                            new IconData(
                                    progressIcon(ARROW, 115, 80, 256, 0),
                                    progressIcon(FLAME, 76, 53, 256, 30),
                                    progressIcon(FLAME, 164, 53, 256, 60)
                            ),
                            new SlotData(
                                    slotSettings(
                                            addPlayerSlots(true)
                                    ),
                                    inputSlots(
                                            slot(56, 70),
                                            slot(74, 70),
                                            slot(92, 70),
                                            slot(56, 88),
                                            slot(74, 88),
                                            slot(92, 88)
                                    ),

                                    outputSlots(
                                            slot(144, 70),
                                            slot(162, 70),
                                            slot(180, 70),
                                            slot(144, 88),
                                            slot(162, 88),
                                            slot(180, 88)
                                    )
                            )
                    )
            );

    // FACTORIES \\
    public static final Factory COKE_OVEN =
            new Factory(
                    FactoryIDs.COKE_OVEN,
                    MULTIBLOCK,
                    new AscentGUI(
                            new CosmeticData(
                                    "#807878",
                                    "Coke Oven"
                            ),
                            new IconData(
                                    progressIcon(FLAME, 121, 81, 256, 0)
                            ),
                            new SlotData(
                                    slotSettings(
                                            addPlayerSlots(true)
                                    ),
                                    inputSlots(
                                            slot(95, 79)
                                    ),
                                    outputSlots(
                                            largeSlot(143, 75)
                                    )
                            )
                    )
            );
}
