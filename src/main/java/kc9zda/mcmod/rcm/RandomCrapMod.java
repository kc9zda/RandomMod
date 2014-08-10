package kc9zda.mcmod.rcm;

import kc9zda.mcmod.rcm.extras.RCMExtras;
import kc9zda.mcmod.rcm.worldgen.WorldGenDebug;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = RandomCrapMod.MODID, version = RandomCrapMod.VERSION)
public class RandomCrapMod {
	public static final String MODID = "rcm";
	public static final String VERSION = "0.0.1";
	
	@Instance(value = RandomCrapMod.MODID)
	public static RandomCrapMod instance;
	
	@SidedProxy(clientSide="kc9zda.mcmod.rcm.client.ClientProxy", serverSide="kc9zda.mcmod.rcm.CommonProxy")
    public static CommonProxy proxy;
	
	public static WorldGenDebug debugWorldGen;
	
	/* Commented areas are for future use */
	
	
	//@EventHandler
	//public void preInit(FMLPreInitializationEvent e) {
		//RCMInterop.preInit(e);
		//RCMNetwork.preInit(e);
		//RCMMoney.preInit(e);
	//}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		//RCMInterop.init(e);
		//RCMNetwork.init(e);
		//RCMMoney.init(e);
		RCMExtras.init(e);
		debugWorldGen = new WorldGenDebug();
		
		GameRegistry.registerWorldGenerator(debugWorldGen, 0);
	}
	
	/*
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		RCMInterop.postInit(e);
		RCMNetwork.postInit(e);
		RCMMoney.postInit(e);
	}
	*/
}
