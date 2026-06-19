package com.SprintXXL.primitiveindustry.client;

import com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry;
import com.SprintXXL.primitiveindustry.industryblocks.registry.IndustryBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelHandler {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {

        for (Block block : IndustryBlockRegistry.getBlocks()) {

            Item item = Item.getItemFromBlock(block);

            ModelLoader.setCustomModelResourceLocation(
                    item,
                    0,
                    new ModelResourceLocation(item.getRegistryName(), "inventory")
            );
        }

        for (Block block : FactoryRegistry.getBlocks()) {

            Item item = Item.getItemFromBlock(block);

            ModelLoader.setCustomModelResourceLocation(
                    item,
                    0,
                    new ModelResourceLocation(item.getRegistryName(), "inventory")
            );
        }
    }
}
