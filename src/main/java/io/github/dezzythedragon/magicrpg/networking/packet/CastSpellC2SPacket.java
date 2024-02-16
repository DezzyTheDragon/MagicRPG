package io.github.dezzythedragon.magicrpg.networking.packet;

import io.github.dezzythedragon.magicrpg.magic.SpellBase;
import io.github.dezzythedragon.magicrpg.magic.Spells;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CastSpellC2SPacket {
    private int spellID;
    public CastSpellC2SPacket(int spell){
        spellID = spell;
    }
    public CastSpellC2SPacket(FriendlyByteBuf buff){
        spellID = buff.readInt();
    }
    public void toBytes(FriendlyByteBuf buff){
        buff.writeInt(spellID);
    }
    public boolean handle(Supplier<NetworkEvent.Context> event){
        NetworkEvent.Context context = event.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            SpellBase spell = Spells.SPELL_LIST.get(spellID);
            spell.castSpellInit(player);
        });
        return true;
    }
}
