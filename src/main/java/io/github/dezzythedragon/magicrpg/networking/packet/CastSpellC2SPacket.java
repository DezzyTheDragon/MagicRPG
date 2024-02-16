package io.github.dezzythedragon.magicrpg.networking.packet;

import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.magic.PlayerMagicProvider;
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
            // TODO: Attempt to put the spell getting here in the package on the server
            /*player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(playerMagic -> {
                spellID = playerMagic.getSelectedSpell();
            });*/

            if(spellID != -1){
                SpellBase spell = Spells.SPELL_LIST.get(spellID);
                spell.castSpellInit(player);
            }
            else{
                LogUtils.getLogger().info("No spell set");
            }
        });
        return true;
    }
}
