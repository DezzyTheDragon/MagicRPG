package io.github.dezzythedragon.magicrpg.items;

import io.github.dezzythedragon.magicrpg.MagicRPG;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MagicItems {
    public static DeferredRegister<Item> MAGIC_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MagicRPG.MODID);

    public static final CreativeModeTab MAGIC_TAB = new CreativeModeTab("magic_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CONJ_SWORD.get());
        }
    };

    // Conjured Items ===================================================================
    // TODO: create custom class for conjured items
    // TODO: make conjured items slightly emissive if possible
    // TODO: remove the conjured items from the creative menu
    // NOTE: Damage is as follows: Damage = Tier damage + modifier + 1
    public static RegistryObject<Item> CONJ_SWORD = MAGIC_ITEMS.register("conj_sword",
            () -> new ConjuredSwordItem(MagicTiers.LOW, 3, 0.0f, new Item.Properties()));
    public static RegistryObject<Item> CONJ_AXE = MAGIC_ITEMS.register("conj_axe",
            () -> new AxeItem(MagicTiers.LOW, 3, 0.0f, new Item.Properties()));
    public static RegistryObject<Item> CONJ_HOE = MAGIC_ITEMS.register("conj_hoe",
            () -> new HoeItem(MagicTiers.LOW, 3, 0.0f, new Item.Properties()));
    public static RegistryObject<Item> CONJ_SHOVEL = MAGIC_ITEMS.register("conj_shovel",
            () -> new ShovelItem(MagicTiers.LOW, 3, 0.0f, new Item.Properties()));

    // Spell Items =====================================================================
    public static RegistryObject<Item> MAGIC_CRYSTAL = MAGIC_ITEMS.register("magic_crystal", () -> new MagicCrystalItem(new Item.Properties().tab(MAGIC_TAB).stacksTo(16)));
    // TODO: remove the empty scroll/book after implementing a proper spell casting system
    // NOTE: Scrolls and Books are consumable items. Scrolls cast the spell in them. Books teach the spell to the player
    public static RegistryObject<Item> EMPTY_SCROLL = MAGIC_ITEMS.register("empty_scroll", () -> new Item(new Item.Properties().tab(MAGIC_TAB)));
    public static RegistryObject<Item> EMPTY_SPELL_BOOK = MAGIC_ITEMS.register("empty_spell_book", () -> new Item(new Item.Properties().tab(MAGIC_TAB)));
    /*
     * TODO: add a scroll/book for spells
     *  - Conjure tools (Axe, Hoe, Shovel, Pickaxe)
     *  - Conjure Sword
     *  - Sword Dance (Swords that float around the player and shoot out)
     *  - Circle Trap (Magic circle that goes off when an entity steps on it)
     *  - Fire ball
     *  - Ice blast
     *  - Lightning bolt
     *  - Healing
     */
    // TODO: create a creative only book that adds ALL spells
    // TODO: create a set of mana potions
    public static RegistryObject<Item> MANA_POTION = MAGIC_ITEMS.register("mana_potion", () -> new Item(new Item.Properties().tab(MAGIC_TAB)));
    public static RegistryObject<Item> MANA_REGEN_POTION = MAGIC_ITEMS.register("mana_regen_potion", () -> new Item(new Item.Properties().tab(MAGIC_TAB)));

    // Spell Equipment ==================================================================
    public static RegistryObject<Item> BASIC_BAG = MAGIC_ITEMS.register("basic_bag", () -> new BagItem(9, new Item.Properties().tab(MAGIC_TAB).stacksTo(1)));
    // public static RegistryObject<Item> MEDIUM_BAG = MAGIC_ITEMS.register("medium_bag", () -> new BagItem(18, new Item.Properties().tab(MAGIC_TAB)));
    // public static RegistryObject<Item> LARGE_BAG = MAGIC_ITEMS.register("large_bag", () -> new BagItem(27, new Item.Properties().tab(MAGIC_TAB)));


}
