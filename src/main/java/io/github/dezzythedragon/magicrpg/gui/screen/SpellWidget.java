package io.github.dezzythedragon.magicrpg.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.dezzythedragon.magicrpg.MagicRPG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Function;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class SpellWidget extends GuiComponent {
    private static final ResourceLocation WIDGET_TEXTURE = new ResourceLocation(MagicRPG.MODID, "textures/gui/spell_widget.png");
    private final int WIDGET_WIDTH = 75;
    private final int WIDGET_HEIGHT = 25;
    private final SpellWidget.OnPress clickAction;
    private int xPos;
    private int yPos;
    private Component message;
    private int fontColor = 0xFF000000;


    public SpellWidget(Component pText, SpellWidget.OnPress action){
        message = pText;
        clickAction = action;
    }

    public void draw(PoseStack pPoseStack, int pX, int pY){
        xPos = pX;
        yPos = pY;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        //RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, WIDGET_TEXTURE);
        // blit(PostStack, X, Y, UOffset, VOffset, Width, Height) assumes the texture is 256x256
        //  need to add the additional parameters to define the actual texture size
        this.blit(pPoseStack, pX, pY, 0, 0, WIDGET_WIDTH, WIDGET_HEIGHT);
        //NOTE: draw centered string only centers the X component, not the Y. Need to adjust the Y to take into account the height of the font
        drawCenteredString(pPoseStack, Minecraft.getInstance().font, message, pX + (WIDGET_WIDTH / 2), pY + ((WIDGET_HEIGHT - 8) / 2), fontColor);
    }

    public boolean mouseIsOver(int pXOffset, int pYOffset, double pMouseX, double pMouseY){
        boolean mouseXOver = pMouseX > (xPos + pXOffset) && pMouseX < (xPos + WIDGET_WIDTH + pXOffset);
        boolean mouseYOver = pMouseY > (yPos + pYOffset) && pMouseY < (yPos + WIDGET_HEIGHT + pYOffset);
        return mouseXOver && mouseYOver;
    }

    public boolean mouseClicked(int pXOffset, int pYOffset, double pMouseX, double pMouseY) {
        if(mouseIsOver(pXOffset, pYOffset, pMouseX, pMouseY)){
            Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            onPress();
            return true;
        }else {
            return false;
        }
    }

    public void onPress(){
        this.clickAction.onPress(this);
    }

    @OnlyIn(Dist.CLIENT)
    public interface OnPress{
        void onPress(SpellWidget pWidget);
    }

}
