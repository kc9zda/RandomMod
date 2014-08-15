package kc9zda.mcmod.rcm.extras.itementity;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import kc9zda.mcmod.rcm.itementity.ItemEntityInventory;

public class ItemEntityPortableCraftingTable extends ItemEntityInventory {

	public ItemEntityPortableCraftingTable(ItemStack i) {
		super(9, i);
	}

	public void readCraftingMatrix(InventoryCrafting craftingMatrix) {
		int i = 1;
		
		while (i < 9) {
			craftingMatrix.setInventorySlotContents(i-1, this.getStackInSlot(i));
			i++;
		}
	}
	
	public void writeCraftingMatrix(InventoryCrafting craftingMatrix) {
		int i = 1;
		
		while (i < 9) {
			this.setInventorySlotContents(i, craftingMatrix.getStackInSlot(i-1));
			i++;
		}
	}
}
