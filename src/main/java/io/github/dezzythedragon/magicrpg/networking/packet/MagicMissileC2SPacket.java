package io.github.dezzythedragon.magicrpg.networking.packet;

import io.github.dezzythedragon.magicrpg.entity.MagicEntities;
import io.github.dezzythedragon.magicrpg.entity.MagicMissileProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MagicMissileC2SPacket {
    public MagicMissileC2SPacket(){}
    public MagicMissileC2SPacket(FriendlyByteBuf buff){}
    public void toBytes(FriendlyByteBuf buff){}
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            //BlockPos summonPos = player.blockPosition();
            //summonPos.offset(0, 2, 0);
            //summonPos.relative(player.getDirection());
            //summonPos.above(2);

            //MagicEntities.MAGIC_MISSILE_PROJECTILE.get().spawn(level, null, null, player.blockPosition().above(2), MobSpawnType.COMMAND, true, false);
            MagicMissileProjectile missile = new MagicMissileProjectile(level, player);
            missile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
            level.addFreshEntity(missile);

            //TEMP Testing
            //ArrowItem arrowItem = (ArrowItem) Items.ARROW;
            //AbstractArrow abstractArrow = arrowItem.createArrow(level, new ItemStack(Items.ARROW), player);
            //abstractArrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 30.0F, 1.0F);
        });
        return true;
    }

}
