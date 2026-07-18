package com.SprintXXL.primitiveindustry.ascent;

import com.SprintXXL.primitiveindustry.factory.definitions.ModFactories;
import com.SprintXXL.primitiveindustry.industryblocks.definitions.ModIndustryBlocks;
import com.sprintxxl.ascenthub.definitions.AscentDefinition;
import com.sprintxxl.ascenthub.definitions.AscentDefinitionProvider;
import com.sprintxxl.ascenthub.definitions.DefinitionRegistrar;

import static com.SprintXXL.primitiveindustry.Reference.MODID;
import static com.sprintxxl.ascenthub.definitions.registry.DefinitionProviderRegistry.registerProvider;

public final class IndustryDefinitionProvider implements AscentDefinitionProvider {

    private IndustryDefinitionProvider() {}

    public static void initIndustryDefinitionProvider() {
        registerProvider(MODID, new IndustryDefinitionProvider());
    }

    @Override
    public void registerDefinitions(DefinitionRegistrar<AscentDefinition> registrar) {

        ModFactories.initFactoryDefinitions(registrar::register);
        ModIndustryBlocks.initIndustryBlockDefinitions(registrar::register);
    }
}
