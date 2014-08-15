package kc9zda.mcmod.rcm.itementity;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class ItemEntity {
	
	public ItemEntity(ItemStack i) {
		this.itemStack = i;
	}
	
	protected World worldObj;
	protected boolean valid;
	public int itemMetadata = 0;
	public Item itemType;
	protected ItemStack itemStack;
	
	public abstract void readFromNBT(NBTTagCompound nbt);
	public abstract void writeToNBT(NBTTagCompound nbt);
	
}
