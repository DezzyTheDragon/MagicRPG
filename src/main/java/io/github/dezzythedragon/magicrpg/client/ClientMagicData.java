package io.github.dezzythedragon.magicrpg.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientMagicData {
    private static int playerMana;
    private static int playerSelection;

    public static void setMana(int mana){
        playerMana = mana;
    }
    public static void setSelection(int selection) {
        playerSelection = selection;
    }

    public static int getPlayerMana(){
        return playerMana;
    }

    public static int getPlayerSelection(){
        return playerSelection;
    }
}
