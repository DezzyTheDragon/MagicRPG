package io.github.dezzythedragon.magicrpg.entity;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import java.util.List;

/*
 * A basic projectile the player can shoot using the "Magic Missile" spell
 */

public class MagicMissileProjectile extends AbstractHurtingProjectile {
    public MagicMissileProjectile(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MagicMissileProjectile(Level pLevel){
        super(MagicEntities.MAGIC_MISSILE_PROJECTILE.get(), pLevel);
    }

    // Preferred constructor as it sets the position to the entity shooting it
    public MagicMissileProjectile(Level pLevel, LivingEntity pShooter){
        super(MagicEntities.MAGIC_MISSILE_PROJECTILE.get(), pShooter.getX(), pShooter.getY() + 1.5D, pShooter.getZ(), 0.0D, 0.0D, 0.0D, pLevel);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    // Function that runs when the projectile hits something
    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        if(!this.level.isClientSide){
            if(pResult.getType() == HitResult.Type.ENTITY){
                List<LivingEntity> entityList = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0D));
                if(!entityList.isEmpty()){
                    for(LivingEntity livingEntity : entityList){
                        double dist = this.distanceToSqr(livingEntity);
                        if(dist < 16.0D){
                            // TODO: Add a way to modify the amount of damage the projectile does using level ups
                            livingEntity.hurt(DamageSource.MAGIC, 1);
                        }
                    }
                }
            }
            this.discard();
        }
    }

    // TODO: Change the particle trail for the projectile to something more appropriate.
    //          overriding the function `ParticleOptions getTrailParticle()`
}
