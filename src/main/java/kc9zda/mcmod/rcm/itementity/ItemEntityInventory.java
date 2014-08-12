package kc9zda.mcmod.rcm.itementity;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemEntityInventory extends ItemEntity implements IInventory {

	public int invSize = 0;
	public String invName;
	private ItemStack inv[];
	
	public ItemEntityInventory(int size) {
		this.invSize = size;
		this.inv = new ItemStack[size];
	}
	
	@Override
	public int getSizeInventory() {
		return this.invSize;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return this.inv[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int delta) {
		ItemStack i = this.getStackInSlot(slot);
		if (delta > i.stackSize) {
			this.inv[slot] = null;
			return null;
		} else {
			i.stackSize -= delta;
		}
		return i;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return this.getStackInSlot(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		this.inv[slot] = itemStack;
	}

	@Override
	public String getInventoryName() {
		return this.invName;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return (this.invName != null);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		// ???
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		NBTTagList nl = (NBTTagList) nbt.getTag("inv");
		int i = 0;
		int invSz = nbt.getInteger("invSize");
		
		this.invSize = invSz;
		this.inv = new ItemStack[invSz];
		while (i < invSz) {
			this.inv[i+1] = this.readSlotFromNBT(nl.getCompoundTagAt(i));
			i++;
		}
		if (nbt.hasKey("invName")) {
			this.invName = nbt.getString("invName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList nl = new NBTTagList();
		int i = 1;
		NBTTagCompound nbt2 = new NBTTagCompound();
		
		while (i < this.invSize) {
			this.writeSlotToNBT(nbt2, this.inv[i]);
			nl.appendTag(nbt2);
			nbt2 = new NBTTagCompound();
			i++;
		}
		nbt.setInteger("invSize", this.invSize);
		if (this.invName != null) {
			nbt.setString("invName", this.invName);
		}
		nbt.setTag("inv", nl);
	}
	
	public ItemStack readSlotFromNBT(NBTTagCompound nbt) {
		ItemStack returnValue;
		NBTTagCompound is_nbt;
		
		if (!nbt.getBoolean("valid")) return null;
		is_nbt = nbt.getCompoundTag("itemStack");
		returnValue = ItemStack.loadItemStackFromNBT(is_nbt);
		return returnValue;
	}
	
	public void writeSlotToNBT(NBTTagCompound nbt, ItemStack i) {
		NBTTagCompound nbt2 = new NBTTagCompound();
		
		if (i == null) {
			nbt.setBoolean("valid", false);
			return;
		} else {
			nbt.setBoolean("valid", true);
			i.writeToNBT(nbt2);
			nbt.setTag("itemStack", nbt2);
		}
	}
}
