package io.github.dezzythedragon.magicrpg.items;

import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.magic.SpellBase;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ScrollItem extends Item {
    private SpellBase spell;

    public ScrollItem(Properties pProperties) {
        super(pProperties);
        spell = null;
    }
    public ScrollItem(Properties pProperties, SpellBase pSpell){
        super(pProperties);
        spell = pSpell;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide){
            if(spell != null){
                spell.castSpell();
            }
            else{
                pPlayer.sendSystemMessage(Component.translatable("spell.magicrpg.blank"));
            }
        }
        else{
            pPlayer.getInventory().getSelected().shrink(1);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        if(spell != null){
            return true;
        }
        return false;
    }
}
