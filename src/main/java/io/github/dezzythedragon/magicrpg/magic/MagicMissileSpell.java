package io.github.dezzythedragon.magicrpg.magic;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;

public class MagicMissileSpell extends SpellBase{
    public MagicMissileSpell(int pSpellID) {
        super(pSpellID, Component.literal("T_Magic Missile"));
    }

    @Override
    public void castSpellInit() {
        super.castSpellInit();
    }

    @Override
    public void castSpell() {
        LogUtils.getLogger().info("MAGIC MISSILE");
    }

    @Override
    public void castSpellDeconstruct() {
        super.castSpellDeconstruct();
    }
}
