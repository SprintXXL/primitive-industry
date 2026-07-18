package com.SprintXXL.primitiveindustry.factory.recipe.data;

import com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.RecipeResource;

import java.util.List;

public interface FactoryRecipeData {

    int getDurationTicks();

    List<RecipeResource> getInputs();

    List<RecipeResource> getOutputs();
}
