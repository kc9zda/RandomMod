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
	public static final boolean DEBUG_MODE = false;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		/* commented so eclipse doesn't complain */
		/*
		if (!hasBeenGenerated && DEBUG_MODE) {
			hasBeenGenerated = true;
			if (!world.isRemote) {
				int sx;
				int sy;
				int sz;
				TileEntityChest te;
				sx = world.getSpawnPoint().posX;
				sz = world.getSpawnPoint().posZ;
				sy = this.findGroundY(world, sx, world.getSpawnPoint().posY ,sz);
				System.out.println("sx = "+sx);
				System.out.println("sy = "+sy);
				System.out.println("sz = "+sz);
				world.setBlock(sx, sy, sz+1, Blocks.chest);
				te = (TileEntityChest)world.getTileEntity(sx, sy, sz+1);
				if (te == null) System.err.println("CHEST TILE ENTITY IS NULL!!!");
				te.setInventorySlotContents(1, new ItemStack(RCMExtras.snowTnt));
			}
		}
		*/
	}

	private int findGroundY(World w, int sx, int sy, int sz) {
		int i = 0;
		
		while (!w.canBlockSeeTheSky(sx, sy+i, sz)) {
			i++;
		}
		return sy+i;
	}

}
