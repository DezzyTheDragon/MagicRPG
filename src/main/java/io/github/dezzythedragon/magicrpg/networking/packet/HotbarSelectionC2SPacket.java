package io.github.dezzythedragon.magicrpg.networking.packet;

import io.github.dezzythedragon.magicrpg.magic.PlayerMagicProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HotbarSelectionC2SPacket {
    private boolean isMovingRight;
    public HotbarSelectionC2SPacket(boolean movingRight){
        isMovingRight = movingRight;
    }
    public HotbarSelectionC2SPacket(FriendlyByteBuf buff){
        isMovingRight = buff.readBoolean();
    }
    public void toBytes(FriendlyByteBuf buff){
        buff.writeBoolean(isMovingRight);
    }
    public boolean handle(Supplier<NetworkEvent.Context> event){
        NetworkEvent.Context context = event.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(playerMagic -> {
                if(isMovingRight){
                    playerMagic.selectRight(player);
                }
                else{
                    playerMagic.selectLeft(player);
                }
            });
        });
        return true;
    }
}
