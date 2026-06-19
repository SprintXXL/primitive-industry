package com.SprintXXL.primitiveindustry.registry;

import com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry;
import com.SprintXXL.primitiveindustry.industryblocks.registry.IndustryBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ForgeRegistry {

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

        event.getRegistry().registerAll(
                IndustryBlockRegistry.getBlocks().toArray(new Block[0])
        );

        event.getRegistry().registerAll(
                FactoryRegistry.getBlocks().toArray(new Block[0])
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

        for (Block block : IndustryBlockRegistry.getBlocks()) {
            event.getRegistry().register(createItemBlock(block));
        }

        for (Block block : FactoryRegistry.getBlocks()) {
            event.getRegistry().register(createItemBlock(block));
        }
    }

    private static ItemBlock createItemBlock(Block block) {
        return (ItemBlock) new ItemBlock(block)
                .setRegistryName(block.getRegistryName());
    }
}
