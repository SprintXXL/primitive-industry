package com.SprintXXL.primitiveindustry.industryblocks.registry;

import com.SprintXXL.primitiveindustry.industryblocks.IndustryBlock;
import com.SprintXXL.primitiveindustry.industryblocks.base.IndustryBlockBase;
import net.minecraft.block.Block;

import java.util.*;

import static com.SprintXXL.primitiveindustry.industryblocks.definitions.ModIndustryBlocks.initIndustryBlockDefinitions;

public class IndustryBlockRegistry {

    private IndustryBlockRegistry() {}

    private static boolean initialized = false;

    public static void initIndustryBlocks() {

        if (initialized) {
            return;
        }

        initialized = true;

        initIndustryBlockDefinitions();
        createBlocks();
    }

    private static final List<IndustryBlock> ALL_INDUSTRY_BLOCKS = new ArrayList<>();

    private static final Map<String, IndustryBlock> INDUSTRY_BLOCKS = new HashMap<>();

    public static IndustryBlock getIndustryBlock(String id) {
        return INDUSTRY_BLOCKS.get(id);
    }

    public static List<IndustryBlock> getAllIndustryBlocks() {
        return Collections.unmodifiableList(ALL_INDUSTRY_BLOCKS);
    }

    public static void register(IndustryBlock industryBlock) {
        ALL_INDUSTRY_BLOCKS.add(industryBlock);
        INDUSTRY_BLOCKS.put(industryBlock.getID(), industryBlock);
    }

    private static final Map<IndustryBlock, Block> BLOCKS = new HashMap<>();

    public static Collection<Block> getBlocks() {
        return BLOCKS.values();
    }

    private static void createBlocks() {

        for (IndustryBlock industryBlock : ALL_INDUSTRY_BLOCKS) {
            Block block = new IndustryBlockBase(industryBlock.getID());
            BLOCKS.put(industryBlock, block);
        }
    }
}
