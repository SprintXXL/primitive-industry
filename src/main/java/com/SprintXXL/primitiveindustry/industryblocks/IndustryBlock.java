package com.SprintXXL.primitiveindustry.industryblocks;

import com.sprintxxl.ascenthub.definitions.AscentDefinition;

public class IndustryBlock implements AscentDefinition {

    private final String id;

    public IndustryBlock(
            String id
    ) {
        this.id = id;
    }

    @Override
    public String getID() {
        return id;
    }
}
