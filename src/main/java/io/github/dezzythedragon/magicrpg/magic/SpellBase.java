package io.github.dezzythedragon.magicrpg.magic;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class SpellBase {
    private final int ID;
    private final Component NAME;
    protected int manaCost = 0;
    protected boolean canCast = false;
    protected ResourceLocation icon = TextureManager.INTENTIONAL_MISSING_TEXTURE;

    public SpellBase(int pSpellID, Component pSpellName){
        ID = pSpellID;
        NAME = pSpellName;
    }

    //Check if player has available mana and consume it
    public void castSpellInit(ServerPlayer caster){
        caster.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifPresent(playerMagic -> {
            if(playerMagic.getManaLevel() >= manaCost){
                playerMagic.removeMana(manaCost, caster);
                playerMagic.resetTime();

                castSpell(caster);
            }
            else{
                caster.sendSystemMessage(Component.translatable("spell.magicrpg.low_mana").withStyle(ChatFormatting.RED));
            }
        });

        //if(Minecraft.getInstance().level.isClientSide){
            //Get mana capability from player
            //Check if there is enough mana to cast the spell
                //Consume mana from player
                //canCast = true
            //else
                //tell player not enough mana
        //}
    }

    //Cast the actual spell
    public void castSpell(ServerPlayer caster){
        LogUtils.getLogger().info("Default Spell Implementation");
    }

    //Do any cleanup needed for the spell
    public void castSpellEnd(ServerPlayer caster){}

    //Quick check for if the player is crouching allowing possible modification to
    //  how a spell is cast
    /*protected boolean isCrouching(){
        if(Minecraft.getInstance().level.isClientSide){
            if(Minecraft.getInstance().player.isCrouching()){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }*/

    public int getID(){
        return ID;
    }

    public Component getSpellName(){
        return NAME;
    }

    public ResourceLocation getIcon() { return icon; }
}
