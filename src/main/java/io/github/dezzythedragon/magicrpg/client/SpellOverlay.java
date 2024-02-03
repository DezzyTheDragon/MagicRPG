package io.github.dezzythedragon.magicrpg.client;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.dezzythedragon.magicrpg.MagicRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

@OnlyIn(Dist.CLIENT)
public class SpellOverlay {
    private static final ResourceLocation SPELL_SELECTOR = new ResourceLocation(MagicRPG.MODID, "textures/gui/spell_overlay.png");
    private static final ResourceLocation MANA_EMPTY = new ResourceLocation(MagicRPG.MODID, "textures/gui/spell_overlay.png");
    private static final ResourceLocation MANA_FULL = new ResourceLocation(MagicRPG.MODID, "textures/gui/spell_overlay.png");

    public static final IGuiOverlay HUD_SPELL = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = (screenWidth / 8) - 31;
        int y = screenHeight - 23;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, SPELL_SELECTOR);
        gui.blit(poseStack, x, y, 0, 0, 62, 23);
    });

    public static final IGuiOverlay HUD_MANA = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = (screenWidth / 8) - 31;
        int y = screenHeight - 30;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, MANA_EMPTY);
        gui.blit(poseStack, x, y, 1, 27, 65, 5);

        //int manaAmount = (int)(texture_width * percentage of current manna)
        int manaAmount = 65;

        RenderSystem.setShaderTexture(0, MANA_FULL);
        gui.blit(poseStack, x, y, 1, 33, manaAmount, 5);
    });
}