package com.SprintXXL.primitiveindustry.factory.recipe;

import com.sprintxxl.ascentresourcerecipeindex.recipes.api.RecipeResourceMatcher;
import com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.RecipeResource;
import com.sprintxxl.ascentresourcerecipeindex.recipes.registry.AscentRecipeRegistry;
import net.minecraft.item.ItemStack;

import java.util.List;

public class FactoryRecipeMatcher {

    public static FactoryRecipe findMatchingRecipe(String factoryID, List<ItemStack> inputStacks) {

        if (factoryID == null || inputStacks == null) {
            return null;
        }

        for (FactoryRecipe recipe : AscentRecipeRegistry.getRecipes(FactoryRecipe.class)) {

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

        if (recipe.getInputs().size() > inputStacks.size()) {
            return false;
        }

        for (int i = 0; i < recipe.getInputs().size(); i++) {
            if (!RecipeResourceMatcher.matches(recipe.getInputs().get(i), inputStacks.get(i))) {
                return false;
            }
        }

        return true;
    }

    private static boolean matchesPooledInputs(FactoryRecipe recipe, List<ItemStack> inputStacks) {

        for (RecipeResource input : recipe.getInputs()) {

            int found = 0;

            if (input.hasInputSlotOverride()) {

                int slotIndex = input.getInputSlotOverride() - 1;

                if (slotIndex < 0 || slotIndex >= inputStacks.size()) {
                    return false;
                }

                ItemStack stack = inputStacks.get(slotIndex);

                if (!RecipeResourceMatcher.matches(input, stack)) {
                    return false;
                }

                if (stack.getCount() < input.getCount()) {
                    return false;
                }

                continue;
            }

            for (ItemStack stack : inputStacks) {

                if (RecipeResourceMatcher.matches(input, stack)) {
                    found += stack.getCount();
                }

                if (found >= input.getCount()) {
                    break;
                }
            }

            if (found < input.getCount()) {
                return false;
            }
        }

        return true;
    }
}





















