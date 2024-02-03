package io.github.dezzythedragon.magicrpg.magic;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;

public class SpellBase {
    private final int ID;
    private final Component NAME;

    public SpellBase(int pSpellID, Component pSpellName){
        ID = pSpellID;
        NAME = pSpellName;
    }

    public void castSpellInit(){}

    public void castSpell(){
        LogUtils.getLogger().info("Default Spell Implementation");
    }

    public void castSpellDeconstruct(){}

    public int getID(){
        return ID;
    }

    public Component getSpellName(){
        return NAME;
    }
}
