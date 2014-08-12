package kc9zda.mcmod.rcm.extras;

import kc9zda.mcmod.rcm.CommonProxy;
import kc9zda.mcmod.rcm.RandomCrapMod;
import kc9zda.mcmod.rcm.extras.entity.EntityExplodingSnowball;
import kc9zda.mcmod.rcm.extras.item.ItemExplodingSnowball;
import kc9zda.mcmod.rcm.extras.item.ItemPortableCraftingTable;
import kc9zda.mcmod.rcm.extras.itementity.ItemEntityPortableCraftingTable;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class RCMExtras implements IGuiHandler {
	
	public static final int guiIdPortableCraft = 0;
	public static Item snowTnt;
	public static Item portableCrafting;
	public static RCMExtras instance;

	public static void init(FMLInitializationEvent e) {
		instance = new RCMExtras();
		initItems();
		initEntities();
		initRecipes();
		initGuiHandler();
	}
	
	public static void initItems() {
		snowTnt = new ItemExplodingSnowball();
		portableCrafting = new ItemPortableCraftingTable();
		GameRegistry.registerItem(snowTnt, "snowTnt");
		GameRegistry.registerItem(portableCrafting, "portableCrafting");
	}

	public static void initEntities() {
		EntityRegistry.registerModEntity(EntityExplodingSnowball.class, "Exploding Snowball", 0, RandomCrapMod.instance, 64, 10, true);
	}
	
	public static void initRecipes() {
		GameRegistry.addRecipe(new ItemStack(snowTnt), "STS", "TST", "STS", 'S', Items.snowball, 'T', Blocks.tnt);
		GameRegistry.addRecipe(new ItemStack(portableCrafting), "T ", "S ", 'T', Blocks.crafting_table, 'S', Items.stick);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		Container c = null;
		
		switch (ID) {
		case 0:
			ItemStack i = player.getCurrentEquippedItem();
			ItemPortableCraftingTable item = (ItemPortableCraftingTable) i.getItem();
			ItemEntityPortableCraftingTable iepct = (ItemEntityPortableCraftingTable) item.createItemEntity(0);
			
			if (i == null) System.err.println("i IS NULL!!!");
			if (item == null) System.err.println("item IS NULL!!!");
			if (iepct == null) System.err.println("iepct IS NULL!!!");
			c = item.getContainer(world, iepct, player);
			break;
		default:
			c = null;
			break;
		}
		return c;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		Gui g;
		
		switch (ID) {
		case 0:
			g = new GuiPortableCraftingTable((Container) this.getServerGuiElement(ID, player, world, x, y, z));
			break;
		default:
			g = null;
			break;
		}
		return g;
	}
	
	public static void initGuiHandler() {
		CommonProxy.registerGuiHandler(0, instance); // Portable Crafting Table
	}
}
