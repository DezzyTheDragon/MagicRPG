package io.github.dezzythedragon.magicrpg.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//TODO: Check if this is allowed
@OnlyIn(Dist.CLIENT)
public class ClientManaData {
    private static int playerMana;

    public static void set(int mana){
        playerMana = mana;
    }

    public static int getPlayerMana(){
        return playerMana;
    }
}
