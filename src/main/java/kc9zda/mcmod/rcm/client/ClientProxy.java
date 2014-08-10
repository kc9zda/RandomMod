package kc9zda.mcmod.rcm.client;

import kc9zda.mcmod.rcm.CommonProxy;
import kc9zda.mcmod.rcm.extras.RCMExtras;
import kc9zda.mcmod.rcm.extras.entity.EntityExplodingSnowball;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityExplodingSnowball.class, new RenderSnowball(Items.snowball));
	}

}
