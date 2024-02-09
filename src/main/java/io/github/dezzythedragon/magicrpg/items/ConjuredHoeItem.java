package io.github.dezzythedragon.magicrpg.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class ConjuredHoeItem extends HoeItem implements ConjureInterface {
    public ConjuredHoeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
