package kc9zda.mcmod.rcm.extras.item;

import kc9zda.mcmod.rcm.RandomCrapMod;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemExplodingSnowball extends Item {
	
	@SideOnly(Side.CLIENT)
	public IIcon itemIcon;

	public ItemExplodingSnowball() {
		super();
		this.maxStackSize=16;
		this.setUnlocalizedName("snowTnt");
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
		float f = 2.0F;
		ItemStack r = i;
		
		if (!p.capabilities.isCreativeMode) {
			r.stackSize--;
		}
		w.playSoundAtEntity(p, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!w.isRemote)
        {
            //w.spawnEntityInWorld(new EntityExplodingSnowball(w, p));
			addChat(p,"Hello World!");
			if (p.isSneaking()) {
				addChat(p,"Player Is Sneaking");
			}
        }
		return r;
	}
	
	
	private void addChat(EntityPlayer p, String s) {
		p.addChatMessage(new ChatComponentText(s));
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister r) {
		IIcon i = r.registerIcon(RandomCrapMod.MODID+":snowTnt");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int d) {
		return this.itemIcon;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack i) {
		if (i.getItem().equals(this)) {
			return "snowTnt";
		}
		return "null";
	}
}
