package io.github.dezzythedragon.magicrpg.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.util.KeyBinds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LevelWindowScreen extends Screen {
    private static ResourceLocation WINDOW_TEXTURE = new ResourceLocation(MagicRPG.MODID, "textures/gui/level_window.png");
    private static final int WINDOW_WIDTH = 256;
    private static final int WINDOW_HEIGHT = 235;
    private static final int BUTTON_WIDTH = 50;
    private static final int BUTTON_HEIGHT = 20;
    private int windowLeftEdge;
    private int windowTopEdge;

    private SpellTab spellTab;

    public LevelWindowScreen(Component component) {
        super(component);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        // Checks if the given key press matches what is bound to the open
        //  level up screen. If it is clear the screen and free the mouse.
        if(KeyBinds.BIND_LEVEL_SCREEN.matches(pKeyCode, pScanCode)){
            minecraft.setScreen((Screen)null);
            minecraft.mouseHandler.grabMouse();
            return true;
        }else{
            return super.keyPressed(pKeyCode, pScanCode, pModifiers);
        }
    }

    @Override
    protected void init() {
        spellTab = new SpellTab();

        windowLeftEdge = (width - WINDOW_WIDTH) / 2;
        windowTopEdge = (height - WINDOW_HEIGHT) / 2;

        super.init();

        // TODO: The masking that occurs in the renderInside function result in the buttons not being rendered (even though they are there)
        //          need to figure out how to prevent that from happening might have to replace with my own rendered button
        /*
        addRenderableWidget(new Button(windowLeftEdge - BUTTON_WIDTH, windowTopEdge, BUTTON_WIDTH, BUTTON_HEIGHT,
                Component.translatable("button.magicrpg.level_button"), this::displayLeveling));
        addRenderableWidget(new Button(windowLeftEdge - BUTTON_WIDTH, windowTopEdge + BUTTON_HEIGHT + 1, BUTTON_WIDTH, BUTTON_HEIGHT,
                Component.translatable("button.magicrpg.spells"), this::displaySpells));
         */
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderInside(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderBackground(pPoseStack);
        renderWindow(pPoseStack);

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    private void renderInside(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick){
        PoseStack poseStack = RenderSystem.getModelViewStack();
        poseStack.pushPose();
        poseStack.translate(windowLeftEdge + 10, windowTopEdge + 5, 0.0D);
        RenderSystem.applyModelViewMatrix();
        spellTab.drawContents(pPoseStack, pMouseX, pMouseY, pPartialTick);
        poseStack.popPose();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();




        //Color is taken as an int, here I give it in hex to conform to a more familiar color format.
        //  Color format is 0xAARRGGBB
        //fill(pPoseStack, 4680, 2260, -4680, -2260, (int)0xFF000000);
        /*
        pPoseStack.pushPose();
        pPoseStack.translate(0.0D, 0.0D, 950.0D);
        RenderSystem.enableDepthTest();
        RenderSystem.colorMask(false, false, false, false);
        fill(pPoseStack, 4680, 2260, -4680, -2260, 0xFF000000);
        RenderSystem.colorMask(true, true, true, true);
        pPoseStack.translate(0.0D, 0.0D, -950.0D);
        RenderSystem.depthFunc(518);
        fill(pPoseStack, 234, 113, 0,0, 0xFF000000);
        RenderSystem.depthFunc(515);
        //Get background resource location
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TextureManager.INTENTIONAL_MISSING_TEXTURE);



        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                blit(pPoseStack, 16 * x, 16 * y, 0, 0, 16, 16, 16, 16);
            }
        }

        pPoseStack.pushPose();

         */

    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        //LogUtils.getLogger().info("Drag X: " + pDragX + " | Drag Y: " + pDragY);
        if(pButton != 0){
            return false;
        }
        spellTab.scroll(pDragX, pDragY);
        return true;
        //return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if(pButton == 0){
            spellTab.mouseClicked(windowLeftEdge + 10, windowTopEdge + 5, pMouseX, pMouseY);
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    private void renderWindow(PoseStack pPoseStack){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, WINDOW_TEXTURE);
        int x = (width - WINDOW_WIDTH) / 2;
        int y = (height - WINDOW_HEIGHT) / 2;
        blit(pPoseStack, x, y, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void tempButtonFunc(Button button){
        LogUtils.getLogger().info("Button pressed!");
    }

    public void displayLeveling(Button button){
        LogUtils.getLogger().info("Show level info");
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Showing leveling info"));
    }
    public void displaySpells(Button button){
        LogUtils.getLogger().info("Show spell list");
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Showing spells"));
    }

}
