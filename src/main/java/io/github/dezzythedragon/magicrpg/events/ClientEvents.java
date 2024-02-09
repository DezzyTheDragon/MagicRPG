package io.github.dezzythedragon.magicrpg.events;

import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.client.SpellOverlay;
import io.github.dezzythedragon.magicrpg.gui.screen.LevelWindowScreen;
import io.github.dezzythedragon.magicrpg.magic.Spells;
import io.github.dezzythedragon.magicrpg.networking.MagicMessages;
import io.github.dezzythedragon.magicrpg.networking.packet.MagicMissileC2SPacket;
import io.github.dezzythedragon.magicrpg.networking.packet.TestC2SPacket;
import io.github.dezzythedragon.magicrpg.util.KeyBinds;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = MagicRPG.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents{
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key key){
            if(KeyBinds.BIND_CAST_SPELL.consumeClick()){
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Cast Spell"));
                Spells.MAGIC_MISSILE.castSpell();
            }
            else if(KeyBinds.BIND_NEXT_SPELL.consumeClick()){
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Next Spell"));
            }
            else if(KeyBinds.BIND_PREV_SPELL.consumeClick()){
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Previous Spell"));
            }
            else if(KeyBinds.BIND_LEVEL_SCREEN.consumeClick()){
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Open Level Menu"));
                Minecraft.getInstance().setScreen(new LevelWindowScreen(Component.translatable("magicrpg.gui.level")));
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MagicRPG.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void OnKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinds.BIND_CAST_SPELL);
            event.register(KeyBinds.BIND_PREV_SPELL);
            event.register(KeyBinds.BIND_NEXT_SPELL);
            event.register(KeyBinds.BIND_LEVEL_SCREEN);
        }

        @SubscribeEvent
        public static void registerGUIOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("magic", SpellOverlay.HUD_SPELL);
            event.registerAboveAll("mana", SpellOverlay.HUD_MANA);
        }
    }
}
