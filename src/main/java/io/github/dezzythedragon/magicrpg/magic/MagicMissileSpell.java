package io.github.dezzythedragon.magicrpg.magic;

import io.github.dezzythedragon.magicrpg.networking.MagicMessages;
import io.github.dezzythedragon.magicrpg.networking.packet.MagicMissileC2SPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class MagicMissileSpell extends SpellBase{
    public MagicMissileSpell(int pSpellID) {
        super(pSpellID, Component.translatable("spell.magicrpg.magic_missile"));
        this.manaCost = 10;
    }

    @Override
    public void castSpellInit() {
        super.castSpellInit();
    }

    @Override
    public void castSpell() {
        //TODO: For proper implementation create a single packet that accepts a function
        //  and pass the function to the packet here to keep everything spell related together
        MagicMessages.sendToServer(new MagicMissileC2SPacket());
    }

    @Override
    public void castSpellEnd() {
        super.castSpellEnd();
    }
}
