package io.github.dezzythedragon.magicrpg.magic;

import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.entity.MagicMissileProjectile;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class MagicMissileSpell extends SpellBase{

    public MagicMissileSpell(int pSpellID) {
        super(pSpellID, Component.translatable("spell.magicrpg.magic_missile"));
        this.manaCost = 10;
        this.icon = new ResourceLocation(MagicRPG.MODID, "textures/entity/spells/magic_missile_projectile.png");
    }

    @Override
    public void castSpell(ServerPlayer caster) {
        //TODO: For proper implementation create a single packet that accepts a function
        //  and pass the function to the packet here to keep everything spell related together
        //MagicMessages.sendToServer(new MagicMissileC2SPacket());

        Level level = caster.getLevel();
        MagicMissileProjectile missile = new MagicMissileProjectile(level, caster);
        missile.shootFromRotation(caster, caster.getXRot(), caster.getYRot(), 0.0F, 1.0F, 1.0F);
        level.addFreshEntity(missile);

        castSpellEnd(caster);
    }
}
