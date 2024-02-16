package io.github.dezzythedragon.magicrpg.networking.packet;

import io.github.dezzythedragon.magicrpg.client.ClientMagicData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class HotbarSelectSyncS2CPacket {
    private int selectionIndex;
    public HotbarSelectSyncS2CPacket(int index){
        selectionIndex = index;
    }
    public HotbarSelectSyncS2CPacket(FriendlyByteBuf buff){
        selectionIndex = buff.readInt();
    }
    public void toBytes(FriendlyByteBuf buff){
        buff.writeInt(selectionIndex);
    }
    public boolean handle(Supplier<NetworkEvent.Context> event){
        NetworkEvent.Context context = event.get();
        context.enqueueWork(() -> {
            ClientMagicData.setSelection(selectionIndex);
        });
        return true;
    }
}
