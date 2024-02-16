package io.github.dezzythedragon.magicrpg.events;

import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.client.SpellOverlay;
import io.github.dezzythedragon.magicrpg.gui.screen.LevelWindowScreen;
import io.github.dezzythedragon.magicrpg.magic.Spells;
import io.github.dezzythedragon.magicrpg.networking.MagicMessages;
import io.github.dezzythedragon.magicrpg.networking.packet.CastSpellC2SPacket;
import io.github.dezzythedragon.magicrpg.networking.packet.HotbarSelectionC2SPacket;
import io.github.dezzythedragon.magicrpg.util.KeyBinds;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkEvent;

/*
 * Set up events that run on the client end like key presses or client huds
 */

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = MagicRPG.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        // Key bind events
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key key){
            if(KeyBinds.BIND_CAST_SPELL.consumeClick()){
                // TODO: Get player spell capability, get active spell and cast that
                //Spells.MAGIC_MISSILE.castSpell();
                MagicMessages.sendToServer(new CastSpellC2SPacket(0));
            }
            else if(KeyBinds.BIND_NEXT_SPELL.consumeClick()){
                // TODO: Implement a way to cycle to the next equipped spell
                MagicMessages.sendToServer(new HotbarSelectionC2SPacket(false));
            }
            else if(KeyBinds.BIND_PREV_SPELL.consumeClick()){
                // TODO: Implement a way to cycle to the previous equipped spell
                MagicMessages.sendToServer(new HotbarSelectionC2SPacket(true));
            }
            else if(KeyBinds.BIND_LEVEL_SCREEN.consumeClick()){
                Minecraft.getInstance().setScreen(new LevelWindowScreen(Component.translatable("magicrpg.gui.level")));
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MagicRPG.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        // Register the keys, so they show up and are re-mappable in the options menu
        @SubscribeEvent
        public static void OnKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinds.BIND_CAST_SPELL);
            event.register(KeyBinds.BIND_PREV_SPELL);
            event.register(KeyBinds.BIND_NEXT_SPELL);
            event.register(KeyBinds.BIND_LEVEL_SCREEN);
        }

        // Register player HUDS
        @SubscribeEvent
        public static void registerGUIOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("magic", SpellOverlay.HUD_SPELL);
            event.registerAboveAll("mana", SpellOverlay.HUD_MANA);
        }
    }
}
