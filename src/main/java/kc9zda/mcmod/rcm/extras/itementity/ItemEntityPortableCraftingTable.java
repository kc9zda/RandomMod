package kc9zda.mcmod.rcm.extras.itementity;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import kc9zda.mcmod.rcm.itementity.ItemEntityInventory;

public class ItemEntityPortableCraftingTable extends ItemEntityInventory {

	public ItemEntityPortableCraftingTable() {
		super(10);
	}

	public InventoryCrafting getCraftingInv(Container c) {
		InventoryCrafting ic = new InventoryCrafting(c, 3, 3);
		int i = 1;
		
		if (ic == null) System.err.println("ic IS NULL!!!");
		while (i < 9) {
			ic.setInventorySlotContents(i, this.getStackInSlot(i));
			i++;
		}
		return ic;
	}
}
