package com.SprintXXL.primitiveindustry.factory.definitions;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.data.SlotData;

import static com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry.register;

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
                    true,
                    null,
                    new SlotData[] {
                            new SlotData("input", 56, 35),
                            new SlotData("output", 116, 35)
                    }
            );
}
