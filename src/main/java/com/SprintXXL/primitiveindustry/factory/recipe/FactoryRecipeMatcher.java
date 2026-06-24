package com.SprintXXL.primitiveindustry.factory.recipe;

import com.SprintXXL.primitiverecipeapi.api.RecipeResourceMatcher;
import com.SprintXXL.primitiverecipeapi.factory.FactoryRecipe;
import com.SprintXXL.primitiverecipeapi.factory.FactoryRecipeRegistry;
import com.SprintXXL.primitiverecipeapi.factory.data.BasicFactoryData;
import net.minecraft.item.ItemStack;

import java.util.List;

public class FactoryRecipeMatcher {

    public static FactoryRecipe findMatchingRecipe(String factoryID, List<ItemStack> inputStacks) {

        if (factoryID == null || inputStacks == null) {
            return null;
        }

        for (FactoryRecipe recipe : FactoryRecipeRegistry.getAllRecipes()) {

            if (!factoryID.equals(recipe.getFactoryID())) {
                continue;
            }

            if (matchesRecipe(recipe, inputStacks)) {
                return recipe;
            }
        }

        return null;
    }

    private static boolean matchesRecipe(FactoryRecipe recipe, List<ItemStack> inputStacks) {

        if (recipe == null) {
            return false;
        }

        switch (recipe.getMatchingMode()) {

            case POSITIONAL:
                return matchesPositionalInputs(recipe, inputStacks);

            case POOLED:
                return matchesPooledInputs(recipe, inputStacks);

            default:
                return false;
        }
    }

    private static boolean matchesPositionalInputs(FactoryRecipe recipe, List<ItemStack> inputStacks) {

        if (recipe == null) {
            return false;
        }

        BasicFactoryData data = recipe.getBasicFactoryData();

        if (data.getInputs().size() != inputStacks.size()) {
            return false;
        }

        for (int i = 0; i < data.getInputs().size(); i++) {
            if (!RecipeResourceMatcher.matches(data.getInputs().get(i), inputStacks.get(i))) {
                return false;
            }
        }

        return true;
    }

    private static boolean matchesPooledInputs(FactoryRecipe recipe, List<ItemStack> inputStacks) {
        return false;
    }


}
