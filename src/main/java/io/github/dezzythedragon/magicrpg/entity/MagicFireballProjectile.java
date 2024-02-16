package io.github.dezzythedragon.magicrpg.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class MagicFireballProjectile extends AbstractHurtingProjectile {
    public MagicFireballProjectile(EntityType<? extends AbstractHurtingProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MagicFireballProjectile(Level level, LivingEntity shooter){
        super(MagicEntities.FIREBALL_PROJECTILE.get(), shooter.getX(), shooter.getY() + 1.5D, shooter.getZ(), 0.0D, 0.0D, 0.0D, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(!this.level.isClientSide){
            Entity entity = pResult.getEntity();
            entity.setSecondsOnFire(5);
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);

        if(!this.level.isClientSide){
            this.discard();
        }
    }

    @Override
    protected boolean shouldBurn() {
        return true;
    }
}
