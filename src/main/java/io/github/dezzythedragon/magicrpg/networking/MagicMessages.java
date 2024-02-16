package io.github.dezzythedragon.magicrpg.networking;

import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.networking.packet.MagicMissileC2SPacket;
import io.github.dezzythedragon.magicrpg.networking.packet.ManaDataSyncS2CPacket;
import io.github.dezzythedragon.magicrpg.networking.packet.TestC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/*
 * Create and register network messages
 */

public class MagicMessages {
    private static SimpleChannel INSTANCE;
    private static int packetID = 0;
    private static int id(){ return packetID++; }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(MagicRPG.MODID, "messages")).networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        INSTANCE = net;

        net.messageBuilder(MagicMissileC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER).decoder(MagicMissileC2SPacket::new)
                .encoder(MagicMissileC2SPacket::toBytes).consumerMainThread(MagicMissileC2SPacket::handle).add();
        net.messageBuilder(ManaDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT).decoder(ManaDataSyncS2CPacket::new)
                .encoder(ManaDataSyncS2CPacket::toBytes).consumerMainThread(ManaDataSyncS2CPacket::handle).add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
