package io.github.dezzythedragon.magicrpg.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

/*
 * Defines the key binds
 */

public class KeyBinds {
    public static final String MAGICRPG_CATEGORY = "key.category.magicrpg";
    public static final String KEY_CAST_SPELL = "key.magicrpg.cast_spell";
    public static final String KEY_NEXT_SPELL = "key.magicrpg.next_spell";
    public static final String KEY_PREV_SPELL = "key.magicrpg.prev_spell";
    public static final String KEY_LEVEL_SCREEN = "key.magicrpg.level_screen";

    public static final KeyMapping BIND_CAST_SPELL =
            new KeyMapping(KEY_CAST_SPELL, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, MAGICRPG_CATEGORY);
    public static final KeyMapping BIND_NEXT_SPELL =
            new KeyMapping(KEY_NEXT_SPELL, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, MAGICRPG_CATEGORY);
    public static final KeyMapping BIND_PREV_SPELL =
            new KeyMapping(KEY_PREV_SPELL, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, MAGICRPG_CATEGORY);
    public static final KeyMapping BIND_LEVEL_SCREEN =
            new KeyMapping(KEY_LEVEL_SCREEN, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, MAGICRPG_CATEGORY);
}
