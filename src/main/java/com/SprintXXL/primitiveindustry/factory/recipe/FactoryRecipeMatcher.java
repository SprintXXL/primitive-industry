package com.SprintXXL.primitiveindustry.factory.recipe;

import com.SprintXXL.primitiverecipeapi.factory.FactoryRecipe;
import com.SprintXXL.primitiverecipeapi.factory.FactoryRecipeRegistry;
import net.minecraft.item.ItemStack;

public class FactoryRecipeMatcher {

    public static FactoryRecipe findMatchingRecipe(String factoryID, ItemStack inputStack) {

        if (factoryID == null || inputStack.isEmpty()) {
            return null;
        }

        for (FactoryRecipe recipe : FactoryRecipeRegistry.getAllRecipes()) {

            if (!factoryID.equals(recipe.getFactoryID())) {
                continue;
            }

            if (recipe.matches(inputStack)) {
                return recipe;
            }
        }

        return null;
    }
}
