package io.github.dezzythedragon.magicrpg.items;

import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class ConjuredShovelItem extends ShovelItem implements ConjureInterface {
    public ConjuredShovelItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
