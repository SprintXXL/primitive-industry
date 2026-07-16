package com.SprintXXL.primitiveindustry.industryblocks.definitions;

import com.SprintXXL.primitiveindustry.industryblocks.IndustryBlock;
import com.sprintxxl.ascenthub.definitions.DefinitionRegistrar;

public class ModIndustryBlocks {

    private ModIndustryBlocks() {}

    public static void registerIndustryBlockDefinitions(DefinitionRegistrar<IndustryBlock> registrar) {

        registrar.register(COKE_OVEN_BRICK);

    }

    public static final IndustryBlock COKE_OVEN_BRICK =
            new IndustryBlock(
                    IndustryBlockIDs.COKE_OVEN_BRICK
            );
}
