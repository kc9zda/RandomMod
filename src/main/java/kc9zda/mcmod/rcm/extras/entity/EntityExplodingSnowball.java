package kc9zda.mcmod.rcm.extras.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExplodingSnowball extends EntityThrowable {

	public EntityExplodingSnowball(World w) {
		super(w);
		// TODO Auto-generated constructor stub
	}

	public EntityExplodingSnowball(World w, EntityLivingBase e) {
		super(w, e);
		// TODO Auto-generated constructor stub
	}

	public EntityExplodingSnowball(World w, double x,
			double y, double z) {
		super(w, x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onImpact(MovingObjectPosition m) {
		if (m.entityHit != null) {
			byte b = 2;
			boolean c = (m.entityHit instanceof EntityCreeper);
			
			if (m.entityHit instanceof EntityBlaze) {
				b = 3;
			}
			m.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)b);
			for (int i = 0; i < 8; ++i)
	        {
	            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	        }
			if (c) {
				EntityCreeper ec = (EntityCreeper)m.entityHit;
				if (this.worldObj.rand.nextInt(5) == 2) {
					int r = 3;
					if (ec.getPowered()) r *= 2;
					float f = ((float)r+0.5F);
					this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, f, false);
				} else {
					this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 0.5F, false);
				}
			} else {
				this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false);
			}
			if (!this.worldObj.isRemote) {
				this.setDead();
			}
		}
	}

}
