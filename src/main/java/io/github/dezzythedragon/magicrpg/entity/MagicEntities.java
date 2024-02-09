package io.github.dezzythedragon.magicrpg.entity;

import io.github.dezzythedragon.magicrpg.MagicRPG;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/*
 * Create and register mod entities
 */

public class MagicEntities {
    public static final DeferredRegister<EntityType<?>> MAGIC_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MagicRPG.MODID);

    public static final RegistryObject<EntityType<MagicMissileProjectile>> MAGIC_MISSILE_PROJECTILE = MAGIC_ENTITIES.register("magic_missile",
            () -> EntityType.Builder.<MagicMissileProjectile>of(MagicMissileProjectile::new, MobCategory.MISC).sized(0.5f, 0.5f).build("magic_missile"));
}
