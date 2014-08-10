package kc9zda.mcmod.rcm.worldgen;

import java.util.Random;

import kc9zda.mcmod.rcm.extras.RCMExtras;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenDebug implements IWorldGenerator {
	
	public static boolean hasBeenGenerated;
	public static final boolean DEBUG_MODE = true;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (!hasBeenGenerated && DEBUG_MODE) {
			hasBeenGenerated = true;
			if (!world.isRemote) {
				int sx;
				int sy;
				int sz;
				TileEntityChest te;
				sx = world.getSpawnPoint().posX;
				sy = world.getSpawnPoint().posY;
				sz = world.getSpawnPoint().posZ;
				world.setBlock(sx, sy, sz+1, Blocks.chest);
				te = (TileEntityChest)world.getTileEntity(sx, sy, sz+1);
				te.setInventorySlotContents(1, new ItemStack(RCMExtras.snowTnt, 1, 0));
			}
		}
	}

}
