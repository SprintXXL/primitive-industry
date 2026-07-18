package com.SprintXXL.primitiveindustry.factory.recipe.data;

import com.sprintxxl.ascentresourcerecipeindex.recipes.reciperesource.RecipeResource;

import java.util.Arrays;
import java.util.List;

public class BasicFactoryData implements FactoryRecipeData {

    private final int duration;
    private final List<RecipeResource> inputs;
    private final List<RecipeResource> outputs;

    public BasicFactoryData(
            int duration,
            List<RecipeResource> inputs,
            List<RecipeResource> outputs
    ) {
        this.duration = duration;
        this.inputs = List.copyOf(inputs);
        this.outputs = List.copyOf(outputs);
    }

    @Override
    public int getDurationTicks() {
        return duration * 20;
    }

    @Override
    public List<RecipeResource> getInputs() {
        return inputs;
    }

    @Override
    public List<RecipeResource> getOutputs() {
        return outputs;
    }

    public static List<RecipeResource> inputs(RecipeResource... inputs) {
        return Arrays.asList(inputs);
    }

    public static List<RecipeResource> outputs(RecipeResource... outputs) {
        return Arrays.asList(outputs);
    }
}
