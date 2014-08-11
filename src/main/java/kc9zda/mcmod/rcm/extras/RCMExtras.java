package kc9zda.mcmod.rcm.extras;

import kc9zda.mcmod.rcm.RandomCrapMod;
import kc9zda.mcmod.rcm.extras.entity.EntityExplodingSnowball;
import kc9zda.mcmod.rcm.extras.item.ItemExplodingSnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class RCMExtras {
	
	public static Item snowTnt;

	public static void init(FMLInitializationEvent e) {
		initItems();
		initEntities();
		initRecipes();
	}
	
	public static void initItems() {
		snowTnt = new ItemExplodingSnowball();
		GameRegistry.registerItem(snowTnt, "snowTnt");
	}

	public static void initEntities() {
		EntityRegistry.registerModEntity(EntityExplodingSnowball.class, "Exploding Snowball", 0, RandomCrapMod.instance, 64, 10, true);
	}
	
	public static void initRecipes() {
		GameRegistry.addRecipe(new ItemStack(snowTnt), "STS", "TST", "STS", 'S', Items.snowball, Blocks.tnt);
	}
}
