package com.SprintXXL.primitiveindustry.industryblocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

import static com.SprintXXL.primitiveindustry.Reference.MODID;

public class IndustryBlockBase extends Block {

    public IndustryBlockBase(String name) {
        super(Material.ANVIL);

        setRegistryName(MODID, name);
        setTranslationKey(MODID + "." + name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
}
