package com.SprintXXL.primitiveindustry;

import com.SprintXXL.primitiveindustry.client.GuiHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.SprintXXL.primitiveindustry.Reference.*;
import static com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry.initFactories;
import static com.SprintXXL.primitiveindustry.industryblocks.registry.IndustryBlockRegistry.initIndustryBlocks;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class PrimitiveIndustry {

    @Mod.Instance(MODID)
    public static PrimitiveIndustry INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        initIndustryBlocks();
        initFactories();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        NetworkRegistry.INSTANCE.registerGuiHandler(PrimitiveIndustry.INSTANCE, new GuiHandler());
    }
}
