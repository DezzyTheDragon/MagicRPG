package io.github.dezzythedragon.magicrpg.magic;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class SpellBase {
    private final int ID;
    private final Component NAME;
    protected int manaCost = 0;
    protected boolean canCast = false;

    public SpellBase(int pSpellID, Component pSpellName){
        ID = pSpellID;
        NAME = pSpellName;
    }

    //Check if player has available mana and consume it
    public void castSpellInit(){
        if(Minecraft.getInstance().level.isClientSide){
            //Get mana capability from player
            //Check if there is enough mana to cast the spell
                //Consume mana from player
                //canCast = true
            //else
                //tell player not enough mana
        }
    }

    //Cast the actual spell
    public void castSpell(){
        LogUtils.getLogger().info("Default Spell Implementation");
    }

    //Do any cleanup needed for the spell
    public void castSpellEnd(){}

    //Quick check for if the player is crouching allowing possible modification to
    //  how a spell is cast
    protected boolean isCrouching(){
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
    }

    public int getID(){
        return ID;
    }

    public Component getSpellName(){
        return NAME;
    }
}
