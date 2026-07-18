package com.SprintXXL.primitiveindustry;

import com.SprintXXL.primitiveindustry.client.GuiHandler;
import com.SprintXXL.primitiveindustry.factory.base.TileEntityFactoryBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.SprintXXL.primitiveindustry.Reference.*;
import static com.SprintXXL.primitiveindustry.ascent.IndustryDefinitionProvider.initIndustryDefinitionProvider;
import static com.SprintXXL.primitiveindustry.ascent.IndustryRecipes.initIndustryRecipes;
import static com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry.initFactoryRegistry;
import static com.SprintXXL.primitiveindustry.industryblocks.registry.IndustryBlockRegistry.initIndustryBlockRegistry;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class PrimitiveIndustry {

    @Mod.Instance(MODID)
    public static PrimitiveIndustry INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        initIndustryBlockRegistry();
        initFactoryRegistry();

        // ARRI \\
        initIndustryRecipes();

        // HUB \\
        initIndustryDefinitionProvider();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        NetworkRegistry.INSTANCE.registerGuiHandler(PrimitiveIndustry.INSTANCE, new GuiHandler());

        GameRegistry.registerTileEntity(TileEntityFactoryBase.class, new ResourceLocation(MODID, "factory_base"));
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
