package io.github.dezzythedragon.magicrpg.items;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

/*
 * Weapon tier enum to define the properties of magic weapons, specifically the conjured items.
 */

public enum MagicTiers implements Tier {
    LOW(1, 100, 4.0f, 1.0f);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;

    private MagicTiers(int pLevel, int pUses, float pSpeed, float pDamage){
        level = pLevel;
        uses = pUses;
        speed = pSpeed;
        damage = pDamage;
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return damage;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getEnchantmentValue() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
