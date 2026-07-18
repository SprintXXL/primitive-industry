package com.SprintXXL.primitiveindustry.factory.recipe;

import com.SprintXXL.primitiveindustry.factory.recipe.data.FactoryRecipeData;
import com.sprintxxl.ascentresourcerecipeindex.recipes.AscentRecipe;
import com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.RecipeResource;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public final class FactoryRecipe implements AscentRecipe {

    private final ResourceLocation recipeID;
    private final String factoryID;
    private final MatchingMode matchingMode;
    private final FactoryRecipeData data;

    public FactoryRecipe(
            ResourceLocation recipeID,
            String factoryID,
            MatchingMode matchingMode,
            FactoryRecipeData data
    ) {
        this.recipeID = recipeID;
        this.factoryID = factoryID;
        this.matchingMode = matchingMode;
        this.data = data;
    }

    @Override
    public ResourceLocation getID() {
        return recipeID;
    }

    public String getFactoryID() {
        return factoryID;
    }

    public MatchingMode getMatchingMode() {
        return matchingMode;
    }

    public FactoryRecipeData getData() {
        return data;
    }

    // API METHODS \\
    public int getDurationTicks() {
        return data.getDurationTicks();
    }

    public List<RecipeResource> getInputs() {
        return data.getInputs();
    }

    public List<RecipeResource> getOutputs() {
        return data.getOutputs();
    }
}
