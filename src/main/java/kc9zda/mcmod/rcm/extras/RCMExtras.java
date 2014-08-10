package kc9zda.mcmod.rcm.extras;

import kc9zda.mcmod.rcm.RandomCrapMod;
import kc9zda.mcmod.rcm.extras.entity.EntityExplodingSnowball;
import kc9zda.mcmod.rcm.extras.item.ItemExplodingSnowball;
import net.minecraft.item.Item;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

public class RCMExtras {
	
	public static Item snowTnt;

	public static void init(FMLInitializationEvent e) {
		initItems();
		initEntities();
		//initRecipes();
	}
	
	public static void initItems() {
		snowTnt = new ItemExplodingSnowball();
	}

	public static void initEntities() {
		EntityRegistry.registerModEntity(EntityExplodingSnowball.class, "Exploding Snowball", 0, RandomCrapMod.instance, 64, 10, true);
	}
}
