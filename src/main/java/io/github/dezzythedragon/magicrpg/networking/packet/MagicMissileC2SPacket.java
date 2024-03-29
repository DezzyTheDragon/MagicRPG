package io.github.dezzythedragon.magicrpg.networking.packet;

import io.github.dezzythedragon.magicrpg.entity.MagicMissileProjectile;
import io.github.dezzythedragon.magicrpg.magic.PlayerMagicProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

/*
 * This packet is intended to test and experiment how to get a single spell to work.
 * It is intended to be removed with a generic packet that accepts a function for
 * spells to pass their functionality to.
 */

@Deprecated
public class MagicMissileC2SPacket {
    public MagicMissileC2SPacket(){} //Standard constructor
    public MagicMissileC2SPacket(FriendlyByteBuf buff){} //Constructor receiving data from network
    public void toBytes(FriendlyByteBuf buff){} //writing data to network
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            // TODO: When shifting to the proper spell implementation do the mana check in the
            //          spell init portion of the spell.
            player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(playerMagic -> {
                if(playerMagic.getManaLevel() >= 10){
                    playerMagic.removeMana(10, player);
                    playerMagic.resetTime();
                    MagicMissileProjectile missile = new MagicMissileProjectile(level, player);
                    missile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
                    level.addFreshEntity(missile);
                }
                else
                {
                    player.sendSystemMessage(Component.translatable("spell.magicrpg.low_mana").withStyle(ChatFormatting.RED));
                }
            });
        });
        return true;
    }

}
