package com.SprintXXL.primitiveindustry.ascent;

import com.SprintXXL.primitiveindustry.factory.definitions.FactoryIDs;
import com.SprintXXL.primitiveindustry.factory.recipe.FactoryRecipe;
import com.SprintXXL.primitiveindustry.factory.recipe.MatchingMode;
import com.SprintXXL.primitiveindustry.factory.recipe.data.BasicFactoryData;
import com.sprintxxl.ascentresourcerecipeindex.recipes.AscentRecipeIDs;

import static com.SprintXXL.primitiveindustry.factory.recipe.data.BasicFactoryData.inputs;
import static com.SprintXXL.primitiveindustry.factory.recipe.data.BasicFactoryData.outputs;
import static com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.RecipeResource.resource;
import static com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.RecipeResource.tag;
import static com.sprintxxl.ascentresourcerecipeindex.recipes.registry.AscentRecipeRegistry.register;
import static com.sprintxxl.ascentresourcerecipeindex.resources.definitions.ResourceCatalog.*;
import static com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.TAGS.*;

public final class IndustryRecipes {

    private IndustryRecipes() {}

    public static void initIndustryRecipes() {

        register(PROCESS_LOG_TO_CHARCOAL);
    }

    // FACTORY RECIPES \\
    public static final FactoryRecipe PROCESS_LOG_TO_CHARCOAL =
            new FactoryRecipe(
                    AscentRecipeIDs.Industry.PROCESS_LOG_TO_CHARCOAL,
                    FactoryIDs.COKE_OVEN,
                    MatchingMode.POSITIONAL,
                    new BasicFactoryData(
                            10,
                            inputs(
                                    tag(ANY_PLANK)
                            ),
                            outputs(
                                    resource(CHARCOAL)
                            )
                    )
            );
}