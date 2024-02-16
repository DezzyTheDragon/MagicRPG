package io.github.dezzythedragon.magicrpg.magic;

import io.github.dezzythedragon.magicrpg.entity.MagicFireballProjectile;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public class FireballSpell extends SpellBase{
    public FireballSpell(int pSpellID) {
        super(pSpellID, Component.translatable("spell.magicrpg.fireball"));
        this.manaCost = 10;
    }

    @Override
    public void castSpell(ServerPlayer caster) {
        Level level = caster.getLevel();

        MagicFireballProjectile fireball = new MagicFireballProjectile(level, caster);
        fireball.shootFromRotation(caster, caster.getXRot(), caster.getYRot(), 0.0F, 1.0F, 1.0F);
        level.addFreshEntity(fireball);

        castSpellEnd(caster);
    }
}
