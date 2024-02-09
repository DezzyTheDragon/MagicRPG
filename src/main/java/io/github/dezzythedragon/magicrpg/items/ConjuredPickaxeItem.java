package io.github.dezzythedragon.magicrpg.items;

import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class ConjuredPickaxeItem extends PickaxeItem implements ConjureInterface {
    public ConjuredPickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
