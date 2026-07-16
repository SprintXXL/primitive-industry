package com.SprintXXL.primitiveindustry.hub;

import com.SprintXXL.primitiveindustry.factory.definitions.ModFactories;
import com.SprintXXL.primitiveindustry.industryblocks.definitions.ModIndustryBlocks;
import com.sprintxxl.ascenthub.definitions.AscentDefinition;
import com.sprintxxl.ascenthub.definitions.AscentDefinitionProvider;
import com.sprintxxl.ascenthub.definitions.DefinitionRegistrar;

public final class IndustryDefinitionProvider implements AscentDefinitionProvider {

    @Override
    public void registerDefinitions(DefinitionRegistrar<AscentDefinition> registrar) {

        ModFactories.registerFactoryDefinitions(definition -> registrar.register(definition));
        ModIndustryBlocks.registerIndustryBlockDefinitions(definition -> registrar.register(definition));
    }
}
