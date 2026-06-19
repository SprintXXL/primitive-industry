package com.SprintXXL.primitiveindustry.industryblocks.definitions;

import com.SprintXXL.primitiveindustry.industryblocks.IndustryBlock;

import static com.SprintXXL.primitiveindustry.industryblocks.registry.IndustryBlockRegistry.register;

public class ModIndustryBlocks {

    private ModIndustryBlocks() {}

    private static boolean initialized = false;

    public static void initIndustryBlockDefinitions() {

        if (initialized) {
            return;
        }

        initialized = true;

        register(COKE_OVEN_BRICK);
    }

    public static final IndustryBlock COKE_OVEN_BRICK =
            new IndustryBlock(
                    IndustryBlockIDs.COKE_OVEN_BRICK
            );
}
