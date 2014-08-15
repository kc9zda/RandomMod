package kc9zda.mcmod.rcm.extras.container;

import kc9zda.mcmod.rcm.extras.itementity.ItemEntityPortableCraftingTable;
import kc9zda.mcmod.rcm.itementity.ItemEntityInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

public class ContainerPortableCraftingTable extends Container {
	
	public InventoryCrafting craftingMatrix = new InventoryCrafting(this, 3, 3);
	private World worldObj;
	private EntityPlayer player;
	private IInventory craftingResult = new InventoryCraftResult();
	private ItemEntityInventory itemEntity;
	private ItemStack itemStack;

	public ContainerPortableCraftingTable(World w, EntityPlayer playerObj, ItemEntityPortableCraftingTable tableEntity, ItemStack i2) {
		this.worldObj = w;
		this.player = playerObj;
		this.itemEntity = tableEntity;
		this.itemStack = i2;
		this.itemEntity.readFromNBT(this.itemStack.stackTagCompound);
		this.addSlotToContainer(new SlotCrafting(playerObj, this.craftingMatrix, this.craftingResult, 0, 124, 35));
		int i;
		int j;
		
		for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(this.craftingMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(this.player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(this.player.inventory, i, 8 + i * 18, 142));
        }
        
        tableEntity.readCraftingMatrix(this.craftingMatrix);
        this.onCraftMatrixChanged(this.craftingMatrix);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	@Override
	public void onCraftMatrixChanged(IInventory inv) {
		this.craftingResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftingMatrix, this.worldObj));
	}
	
	@Override
	public void onContainerClosed(EntityPlayer player) {
		//((ItemEntityPortableCraftingTable) this.itemEntity).writeCraftingMatrix(this.craftingMatrix);
		//this.itemEntity.writeToNBT(this.itemStack.stackTagCompound);
		super.onContainerClosed(player);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.craftingMatrix.getStackInSlotOnClosing(i);

                if (itemstack != null)
                {
                    player.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot)
    {
        ItemStack itemstack = null;
        Slot s = (Slot)this.inventorySlots.get(slot);

        if (s != null && s.getHasStack())
        {
            ItemStack itemstack1 = s.getStack();
            itemstack = itemstack1.copy();

            if (slot == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                s.onSlotChange(itemstack1, itemstack);
            }
            else if (slot >= 10 && slot < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (slot >= 37 && slot < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                s.putStack((ItemStack)null);
            }
            else
            {
                s.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            s.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
