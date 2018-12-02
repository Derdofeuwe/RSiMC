package com.derdofeuwe.rsimcmod;

import com.derdofeuwe.rsimcmod.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RSiMCMain.MODID, name = RSiMCMain.NAME, version = RSiMCMain.VERSION)
public class RSiMCMain
{
    public static final String MODID = "rsimc_rework";
    public static final String NAME = "Runescape in Minecraft 2.0";
    public static final String VERSION = "0.0.0";
    
    @Instance
    private static RSiMCMain instance;
    
    public static RSiMCMain getInstance() {
		return instance;	
    }
    @SidedProxy(serverSide = "com.derdofeuwe.rsimcmod.proxy.CommonProxy", clientSide = "com.derdofeuwe.rsimcmod.proxy.ClientProxy")
    private static CommonProxy proxy;
    public static Configuration config;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	proxy.preinit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    	proxy.init(event);
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event){
    	proxy.postinit(event);
    }
}
