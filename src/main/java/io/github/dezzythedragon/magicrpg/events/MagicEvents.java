package io.github.dezzythedragon.magicrpg.events;

import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.items.ConjuredSwordItem;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/*
 * Catch events fired in the game and execute extra code if they are fired
 */

public class MagicEvents {
    @Mod.EventBusSubscriber(modid = MagicRPG.MODID)
    public static class ForgeEvents{
        // If a conjured item is dropped instead of dropping into the world make it vanish
        // TODO: Change the conjured items to have a base class to collect and handle common logic
        @SubscribeEvent
        public static void onDropConjuredItem(ItemTossEvent event){
            if(event.getEntity().getItem().getItem() instanceof ConjuredSwordItem){
                event.setCanceled(true);
            }
        }
    }
}
