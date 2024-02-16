package io.github.dezzythedragon.magicrpg.magic;

import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.networking.MagicMessages;
import io.github.dezzythedragon.magicrpg.networking.packet.HotbarSelectSyncS2CPacket;
import io.github.dezzythedragon.magicrpg.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerMagic {
    private final String MANA_LEVEL_KEY = "mana_level";
    private final String MANA_CAP_KEY = "mana_cap_mod";
    private final String SPELL_HOTBAR = "spell_hotbar";
    private final String SPELL_SELECTED = "spell_selected";

    private int[] spellHotbar = {0, 1, -1};
    private int spellSelection = 0;

    private final int BASE_MANNA_CAP = 100;
    private int manaCapModifier = 0;
    private int manaLevel = BASE_MANNA_CAP;

    private final int RESTORE_BUFFER_TIME = 40;
    private int restoreTime = 0;

    public PlayerMagic(){}

    public void syncGUI(ServerPlayer player){
        MagicMessages.sendToPlayer(new ManaDataSyncS2CPacket(manaLevel), player);
        MagicMessages.sendToPlayer(new HotbarSelectSyncS2CPacket(spellSelection), player);
    }

    //Get the amount of mana the player has
    public int getManaLevel(){
        return manaLevel;
    }

    //Adds the given amount of mana to the player, should be done server side
    public void addMana(int add, ServerPlayer player){
        manaLevel = Math.min(manaLevel + add, BASE_MANNA_CAP + manaCapModifier);
        MagicMessages.sendToPlayer(new ManaDataSyncS2CPacket(manaLevel), player);
    }

    //Removes the given amount of mana to the player, should be done server side
    public void removeMana(int sub, ServerPlayer player){
        manaLevel = Math.max(manaLevel - sub, 0);
        MagicMessages.sendToPlayer(new ManaDataSyncS2CPacket(manaLevel), player);
    }

    //Moves the hotbar selection to the left
    public void selectLeft(ServerPlayer player){
        spellSelection--;
        if(spellSelection < 0){
            spellSelection = 2;
        }
        //LogUtils.getLogger().info("Selection: " + spellSelection);
        MagicMessages.sendToPlayer(new HotbarSelectSyncS2CPacket(spellSelection), player);
    }

    //Moves the hotbar selection to the right
    public void selectRight(ServerPlayer player){
        spellSelection++;
        if(spellSelection > 2){
            spellSelection = 0;
        }
        //LogUtils.getLogger().info("Selection: " + spellSelection);
        MagicMessages.sendToPlayer(new HotbarSelectSyncS2CPacket(spellSelection), player);
    }

    //Returns the id of the currently selected spell
    public int getSelectedSpell(){
        return spellHotbar[spellSelection];
    }

    //Change the spell id of the specified slot
    public void setSpellHotbar(int spellSlot, int spellID){
        spellHotbar[spellSlot] = spellID;
        //TODO: Sync message
    }

    public void increaseManaCap(int add){
        manaLevel += add;
    }

    //Resets the mana recovery timer
    public void resetTime(){
        restoreTime = 0;
    }

    //Tick that is used for timing the mana recovery
    public void tick(ServerPlayer player){
        //Wait for the specified time before starting recovery
        if(restoreTime >= RESTORE_BUFFER_TIME){
            if(manaLevel < BASE_MANNA_CAP + manaCapModifier){
                //Slow down the recovery preventing it from recovering 20 points per second
                if(restoreTime % 10 == 0){
                    addMana(1, player);
                    //LogUtils.getLogger().info("Mana: " + manaLevel + "/" + (BASE_MANNA_CAP + manaCapModifier));
                }
                restoreTime++;
            }
        }
        else{
            restoreTime++;
        }
    }

    public void copyFrom(PlayerMagic other){
        manaCapModifier = other.manaCapModifier;
        manaLevel = other.manaLevel;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt(MANA_LEVEL_KEY, manaLevel);
        nbt.putInt(MANA_CAP_KEY, manaCapModifier);
        nbt.putIntArray(SPELL_HOTBAR, spellHotbar);
        nbt.putInt(SPELL_SELECTED, spellSelection);
    }

    public void loadNBTData(CompoundTag nbt){
        manaLevel = nbt.getInt(MANA_LEVEL_KEY);
        manaCapModifier = nbt.getInt(MANA_CAP_KEY);
        spellHotbar = nbt.getIntArray(SPELL_HOTBAR);
        spellSelection = nbt.getInt(SPELL_SELECTED);
    }
}
