package io.github.dezzythedragon.magicrpg.events;

import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.items.ConjuredSwordItem;
import io.github.dezzythedragon.magicrpg.items.MagicItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class MagicEvents {
    @Mod.EventBusSubscriber(modid = MagicRPG.MODID)
    public static class ForgeEvents{
        //TODO: Change the conjured items to have a base class to collect and handle common logic
        @SubscribeEvent
        public static void onDropConjuredItem(ItemTossEvent event){
            if(event.getEntity().getItem().getItem() instanceof ConjuredSwordItem){
                event.setCanceled(true);
            }
        }
    }
}
