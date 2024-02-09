package io.github.dezzythedragon.magicrpg.entity;

import com.mojang.logging.LogUtils;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class MagicMissileProjectile extends AbstractHurtingProjectile {
    public MagicMissileProjectile(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MagicMissileProjectile(Level pLevel){
        super(MagicEntities.MAGIC_MISSILE_PROJECTILE.get(), pLevel);
    }

    public MagicMissileProjectile(Level pLevel, LivingEntity pShooter){
        super(MagicEntities.MAGIC_MISSILE_PROJECTILE.get(), pShooter.getX(), pShooter.getY() + 1.5D, pShooter.getZ(), 0.0D, 0.0D, 0.0D, pLevel);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        if(!this.level.isClientSide){
            if(pResult.getType() == HitResult.Type.ENTITY){
                //LogUtils.getLogger().info("Hit an entity");
                List<LivingEntity> entityList = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0D));
                if(!entityList.isEmpty()){
                    for(LivingEntity livingEntity : entityList){
                        double dist = this.distanceToSqr(livingEntity);
                        if(dist < 16.0D){
                            livingEntity.hurt(DamageSource.MAGIC, 1);
                        }
                    }
                }

            }
            this.discard();
        }
    }

    /*public MagicMissileProjectile(Level pLevel, LivingEntity pLivingEntity){
        super(MagicEntities.MAGIC_MISSILE_PROJECTILE.get(), pLivingEntity, pLevel);
    }*/
}
