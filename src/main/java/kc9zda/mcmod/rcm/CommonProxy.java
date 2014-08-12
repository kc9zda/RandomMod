package kc9zda.mcmod.rcm;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {
	
	private static Map<Integer, IGuiHandler> mapGuiHandlers = new HashMap<Integer, IGuiHandler>();
	
	public void registerRenderers() {
		// Nothing here for the server
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		IGuiHandler i = mapGuiHandlers.get(ID);
		if (i != null) return i.getServerGuiElement(ID, player, world, x, y, z);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		IGuiHandler i = mapGuiHandlers.get(ID);
		if (i != null) return i.getClientGuiElement(ID, player, world, x, y, z);
		return null;
	}
	
	public static void registerGuiHandler(int id, IGuiHandler i) {
		mapGuiHandlers.put(id, i);
	}

}
