package kc9zda.mcmod.rcm;

import kc9zda.mcmod.rcm.extras.RCMExtras;
import kc9zda.mcmod.rcm.worldgen.WorldGenDebug;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
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
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
	NetworkRegistry.INSTANCE.registerGuiHandler(RandomCrapMod.instance, proxy);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		RCMExtras.init(e);
		debugWorldGen = new WorldGenDebug();
		
		GameRegistry.registerWorldGenerator(debugWorldGen, 0);
	}
}
