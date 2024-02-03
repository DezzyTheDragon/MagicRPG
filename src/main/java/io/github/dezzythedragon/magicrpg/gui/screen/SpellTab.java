package io.github.dezzythedragon.magicrpg.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import io.github.dezzythedragon.magicrpg.MagicRPG;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SpellTab extends GuiComponent {
    private final int INTERIOR_MASK_HEIGHT = 220;
    private final int INTERIOR_MASK_WIDTH = 240;

    //private final SpellWidget testWidget;
    private List<SpellWidget> spellButtons;
    //private final Button testButton;
    private double scrollX;
    private double scrollY;


    public SpellTab(){
        initSpellList();
        //testWidget = new SpellWidget(Component.literal("Test Spell"), this::testWidgetFunction);
        //testButton = new Button(0, 0, 50, 20, Component.literal("Test"), null);
        //testButton.active = true;

    }

    private void initSpellList(){
        spellButtons = new ArrayList<SpellWidget>();
        spellButtons.add(new SpellWidget(Component.literal("Test Spell 1"), this::testWidgetFunction));
        spellButtons.add(new SpellWidget(Component.literal("Test Spell 2"), this::testWidgetFunction));
        spellButtons.add(new SpellWidget(Component.literal("Test Spell 3"), this::testWidgetFunction));
        spellButtons.add(new SpellWidget(Component.literal("Test Spell 4"), this::testWidgetFunction));
    }

    public void drawContents(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick){
        //Color is taken as an int, here I give it in hex to conform to a more familiar color format.
        //  Color format is 0xAARRGGBB
        //fill(pPoseStack, 4680, 2260, -4680, -2260, (int)0xFF000000);

        //Masking stuff (I think)
        pPoseStack.pushPose();
        pPoseStack.translate(0.0D, 0.0D, 950.0D);
        RenderSystem.enableDepthTest();
        RenderSystem.colorMask(false, false, false, false);
        //fill(pPoseStack, -4800, -2260, 4800, 2260, 0xFF000000);
        fill(pPoseStack, -4800, -2260, 4800, 2260, 0xFF000000);
        RenderSystem.colorMask(true, true, true, true);
        pPoseStack.translate(0.0D, 0.0D, -950.0D);
        RenderSystem.depthFunc(518);
        //fill(pPoseStack, 0, 0, INTERIOR_MASK_WIDTH,INTERIOR_MASK_HEIGHT, 0xFF000000);
        fill(pPoseStack, 0, 0, INTERIOR_MASK_WIDTH,INTERIOR_MASK_HEIGHT, 0xFF000000);
        RenderSystem.depthFunc(515);

        //Background stuff
        //Get background resource location
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, new ResourceLocation(MagicRPG.MODID, "textures/gui/spell_background.png"));
        //RenderSystem.setShaderTexture(0, TextureManager.INTENTIONAL_MISSING_TEXTURE);

        int i = Mth.floor(scrollX);
        int j = Mth.floor(scrollY);
        int k = i % 16;
        int l = j % 16;

        for(int x = -1; x <= 15; ++x){
            for(int y = 0; y <= 14; ++y){
                blit(pPoseStack, k + 16 * x, l + 16 * y, 0, 0, 16, 16, 16, 16);
            }
        }

        //Intractable content
        //testWidget.draw(pPoseStack, i, j);
        //testButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        int offset = 0;
        for(SpellWidget widget : spellButtons){
            widget.draw(pPoseStack, i + 5, j + (30 * offset) + 20);
            offset++;
        }


        //Unsure what this does
        RenderSystem.depthFunc(518);
        pPoseStack.translate(0.0D, 0.0D, -950.0D);
        RenderSystem.colorMask(false, false, false, false);
        fill(pPoseStack, -4800, -2260, 4800, 2260, 0xFF000000);
        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.depthFunc(515);

        pPoseStack.popPose();

    }

    public void scroll(double pDragX, double pDragY){
        //LogUtils.getLogger().info("Drag X: " + pDragX + " | Drag Y: " + pDragY);
        scrollX += pDragX;
        scrollY += pDragY;
    }

    public boolean mouseClicked(int pXOffset, int pYOffset, double pMouseX, double pMouseY){
        /*
        foreach spellButton in spellList
            if spellButton.isMouseOver
                spellButton.doThing
                return true
            else
                return false
         */

        boolean mouseXOverInside = pMouseX > pXOffset && pMouseX < pXOffset + 237;
        boolean mouseYOverInside = pMouseY > pYOffset + 12 && pMouseY < pYOffset + 222;

        if(mouseXOverInside && mouseYOverInside){
            for(SpellWidget widget : spellButtons){
                widget.mouseClicked(pXOffset, pYOffset, pMouseX, pMouseY);
            }
            //LogUtils.getLogger().info("Clicked Inside");
            //testWidget.mouseClicked(pXOffset, pYOffset, pMouseX, pMouseY);
        }

        return false;
    }

    public void testWidgetFunction(SpellWidget widget){
        LogUtils.getLogger().info("Clicked Widget");
    }
}
