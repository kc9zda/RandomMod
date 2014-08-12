package kc9zda.mcmod.rcm.extras.item;

import kc9zda.mcmod.rcm.RandomCrapMod;
import kc9zda.mcmod.rcm.extras.RCMExtras;
import kc9zda.mcmod.rcm.extras.container.ContainerPortableCraftingTable;
import kc9zda.mcmod.rcm.extras.itementity.ItemEntityPortableCraftingTable;
import kc9zda.mcmod.rcm.itementity.IItemEntityProvider;
import kc9zda.mcmod.rcm.itementity.ItemEntity;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemPortableCraftingTable extends Item implements IItemEntityProvider {
	
	public ItemPortableCraftingTable() {
		super();
		setUnlocalizedName("portableCraft");
		setTextureName("rcm:portableCraft");
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack pi, World pw, EntityPlayer p) {
		ItemStack returnValue = pi;
		ItemEntity item = null;
		
		if (pw.isRemote || p.isSneaking()) {
			System.out.println("Exiting onItemRightClick with p.isSneaking()="+p.isSneaking()+" and world remote");
			if (pi == null) System.err.println("pi = null");
			return pi;
		}
		if (!pw.isRemote) {
			System.out.println("Entering p.openGui...");
			p.openGui(RandomCrapMod.instance, RCMExtras.guiIdPortableCraft, pw, (int)p.posX, (int)p.posY, (int)p.posZ);
		}
		return returnValue;
	}

	public ContainerPortableCraftingTable getContainer(World w, ItemEntity ie, EntityPlayer player) {
		//return new ContainerPortableCraftingTable(this.readInv(itemStack), player.inventory);
		if (!(ie instanceof ItemEntityPortableCraftingTable)) System.err.println("ie NOT INSTANCE OF IEPCT");
		return new ContainerPortableCraftingTable(w, player, (ItemEntityPortableCraftingTable) ie);
	}

	@Override
	public ItemEntity createItemEntity(int metadata) {
		return new ItemEntityPortableCraftingTable();
	}

}
