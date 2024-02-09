package io.github.dezzythedragon.magicrpg.items;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Tier;

public class ConjuredAxeItem extends AxeItem implements ConjureInterface {
    public ConjuredAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
