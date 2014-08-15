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
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemPortableCraftingTable extends Item implements IItemEntityProvider {
	
	public ItemPortableCraftingTable() {
		super();
		this.maxStackSize = 1;
		setUnlocalizedName("portableCraft");
		setTextureName("rcm:portableCraft");
		setCreativeTab(CreativeTabs.tabTools);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack pi, World pw, EntityPlayer p) {
		ItemStack returnValue = pi;
		
		if (pw.isRemote || p.isSneaking()) {
			return pi;
		}
		if (!pw.isRemote) {
			p.openGui(RandomCrapMod.instance, RCMExtras.guiIdPortableCraft, pw, (int)p.posX, (int)p.posY, (int)p.posZ);
		}
		return returnValue;
	}

	public ContainerPortableCraftingTable getContainer(World w, ItemEntity ie, EntityPlayer player, ItemStack i) {
		ie.readFromNBT(i.getTagCompound());
		return new ContainerPortableCraftingTable(w, player, (ItemEntityPortableCraftingTable) ie, i);
	}

	@Override
	public ItemEntity createItemEntity(ItemStack i) {
		return new ItemEntityPortableCraftingTable(i);
	}

	@Override
	public void onCreated(ItemStack i, World w, EntityPlayer e) {
		ItemEntity ie;
		
		i.setTagCompound(new NBTTagCompound());
		ie = this.createItemEntity(i);
		ie.writeToNBT(i.stackTagCompound);
	}
	
	public EnumAction getItemUseAction() {
		return EnumAction.block;
	}
}
