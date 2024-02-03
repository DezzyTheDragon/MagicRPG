package io.github.dezzythedragon.magicrpg.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MagicCrystalItem extends Item {
    public MagicCrystalItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
