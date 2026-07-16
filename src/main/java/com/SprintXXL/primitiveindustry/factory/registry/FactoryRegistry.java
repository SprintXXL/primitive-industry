package com.SprintXXL.primitiveindustry.factory.registry;

import com.SprintXXL.primitiveindustry.factory.base.BlockControllerBase;
import com.SprintXXL.primitiveindustry.factory.Factory;
import net.minecraft.block.Block;

import java.util.*;

import static com.SprintXXL.primitiveindustry.factory.definitions.ModFactories.registerFactoryDefinitions;

public class FactoryRegistry {

    private FactoryRegistry() {}

    private static boolean initialized = false;

    public static void initFactoryRegistry() {

        if (initialized) {
            return;
        }

        initialized = true;

        registerFactoryDefinitions(FactoryRegistry::register);
        createBlocks();
    }

    private static final List<Factory> ALL_FACTORIES = new ArrayList<>();

    private static final Map<String, Factory> FACTORIES = new HashMap<>();

    public static Factory getFactory(String id) {
        return FACTORIES.get(id);
    }

    public static List<Factory> getAllFactories() {
        return ALL_FACTORIES;
    }

    public static void register(Factory factory) {

        if (FACTORIES.containsKey(factory.getID())) {
            throw new IllegalArgumentException("Factory already registered: " + factory.getID());
        }

        ALL_FACTORIES.add(factory);
        FACTORIES.put(factory.getID(), factory);
    }

    private static final Map<Factory, Block> BLOCKS = new HashMap<>();

    public static Collection<Block> getBlocks() {
        return BLOCKS.values();
    }

    private static void createBlocks() {

        for (Factory factory : ALL_FACTORIES) {
            Block block = new BlockControllerBase(factory);
            BLOCKS.put(factory, block);
        }
    }
}
