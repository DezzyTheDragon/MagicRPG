package io.github.dezzythedragon.magicrpg.magic;

import java.util.ArrayList;
import java.util.List;

public class Spells {
    public static final MagicMissileSpell MAGIC_MISSILE = new MagicMissileSpell(0);
    public static final FireballSpell FIREBALL = new FireballSpell(1);

    public static final List<SpellBase> SPELL_LIST = new ArrayList<SpellBase>(){{
        add(MAGIC_MISSILE);
        add(FIREBALL);
    }};
}
