package io.github.dezzythedragon.magicrpg.magic;

import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.networking.MagicMessages;
import io.github.dezzythedragon.magicrpg.networking.packet.ManaDataSyncS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

public class PlayerMagic {
    private final String MANA_LEVEL_KEY = "mana_level";
    private final String MANA_CAP_KEY = "mana_cap_mod";

    private final int BASE_MANNA_CAP = 100;
    private int manaCapModifier = 0;
    private int manaLevel = BASE_MANNA_CAP;

    private final int RESTORE_BUFFER_TIME = 40;
    private int restoreTime = 0;

    public PlayerMagic(){}

    public int getManaLevel(){
        return manaLevel;
    }

    public void addMana(int add, ServerPlayer player){
        manaLevel = Math.min(manaLevel + add, BASE_MANNA_CAP + manaCapModifier);
        MagicMessages.sendToPlayer(new ManaDataSyncS2CPacket(manaLevel), player);
    }

    public void removeMana(int sub, ServerPlayer player){
        manaLevel = Math.max(manaLevel - sub, 0);
        MagicMessages.sendToPlayer(new ManaDataSyncS2CPacket(manaLevel), player);
    }

    public void increaseManaCap(int add){
        manaLevel += add;
    }

    public void resetTime(){
        restoreTime = 0;
    }

    public void tick(ServerPlayer player){
        if(restoreTime >= RESTORE_BUFFER_TIME){
            if(manaLevel < BASE_MANNA_CAP + manaCapModifier){
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
    }

    public void loadNBTData(CompoundTag nbt){
        manaLevel = nbt.getInt(MANA_LEVEL_KEY);
        manaCapModifier = nbt.getInt(MANA_CAP_KEY);
    }
}
