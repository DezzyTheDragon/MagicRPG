package io.github.dezzythedragon.magicrpg.events;

import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.items.ConjureInterface;
import io.github.dezzythedragon.magicrpg.items.ConjuredSwordItem;
import io.github.dezzythedragon.magicrpg.magic.PlayerMagic;
import io.github.dezzythedragon.magicrpg.magic.PlayerMagicProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

/*
 * Catch events fired in the game and execute extra code if they are fired
 */

public class MagicEvents {
    @Mod.EventBusSubscriber(modid = MagicRPG.MODID)
    public static class ForgeEvents{
        // If a conjured item is dropped instead of dropping into the world make it vanish
        @SubscribeEvent
        public static void onDropConjuredItem(ItemTossEvent event){
            if(event.getEntity().getItem().getItem() instanceof ConjureInterface){
                event.setCanceled(true);
            }
        }

        //Attach my magic capability to the player
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
            if(event.getObject() instanceof Player){
                if(!event.getObject().getCapability(PlayerMagicProvider.PLAYER_MAGIC).isPresent()){
                    event.addCapability(new ResourceLocation(MagicRPG.MODID, "properties"), new PlayerMagicProvider());
                }
            }
        }

        //When the player dies copy the capability data to the respawned player
        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            if(event.isWasDeath()){
                event.getOriginal().getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(oldStore -> {
                    event.getOriginal().getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(newStore -> {
                        newStore.copyFrom(oldStore);
                    });
                });
            }
        }

        //Register my capability
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
            event.register(PlayerMagic.class);
        }

        //For mana regen
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event){
            if(event.side == LogicalSide.SERVER){
                event.player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(playerMagic -> {
                    playerMagic.tick();
                });
            }
        }
    }
}
