package io.github.dezzythedragon.magicrpg.networking.packet;

import io.github.dezzythedragon.magicrpg.client.ClientManaData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/*
 * This packet syncs the players' mana level from the server
 */

public class ManaDataSyncS2CPacket {
    private final int manaValue;

    public ManaDataSyncS2CPacket(int mana){
        manaValue = mana;
    }
    public ManaDataSyncS2CPacket(FriendlyByteBuf buff){
        manaValue = buff.readInt();
    }
    public void toBytes(FriendlyByteBuf buff){
        buff.writeInt(manaValue);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //Running on client
            ClientManaData.set(manaValue);
        });
        return true;
    }

}
