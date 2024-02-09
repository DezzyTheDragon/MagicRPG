package io.github.dezzythedragon.magicrpg.magic;

import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.networking.MagicMessages;
import io.github.dezzythedragon.magicrpg.networking.packet.MagicMissileC2SPacket;
import net.minecraft.network.chat.Component;

public class MagicMissileSpell extends SpellBase{
    public MagicMissileSpell(int pSpellID) {
        super(pSpellID, Component.literal("T_Magic Missile"));
        this.manaCost = 10;
    }

    @Override
    public void castSpellInit() {
        super.castSpellInit();
    }

    @Override
    public void castSpell() {
        MagicMessages.sendToServer(new MagicMissileC2SPacket());
        //LogUtils.getLogger().info("MAGIC MISSILE");
    }

    @Override
    public void castSpellEnd() {
        super.castSpellEnd();
    }
}
