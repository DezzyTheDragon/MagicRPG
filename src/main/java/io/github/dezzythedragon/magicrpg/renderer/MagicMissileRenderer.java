package io.github.dezzythedragon.magicrpg.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import io.github.dezzythedragon.magicrpg.MagicRPG;
import io.github.dezzythedragon.magicrpg.entity.MagicMissileProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

/*
 * A renderer for the "Magic Missile Projectile"
 */

public class MagicMissileRenderer extends EntityRenderer<MagicMissileProjectile> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(MagicRPG.MODID, "textures/entity/spells/magic_missile_projectile.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);

    public MagicMissileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(MagicMissileProjectile pEntity, float pEntityYaw, float pPartialTick,
                       PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.scale(1.0F, 1.0F, 1.0F);
        pPoseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
        PoseStack.Pose poseStack_pose = pPoseStack.last();
        Matrix4f matrix4f = poseStack_pose.pose();
        Matrix3f matrix3f = poseStack_pose.normal();
        VertexConsumer vertexConsumer = pBuffer.getBuffer(RENDER_TYPE);
        vertex(vertexConsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 0.0F, 0, 1);
        vertex(vertexConsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 0.0F, 1, 1);
        vertex(vertexConsumer, matrix4f, matrix3f, pPackedLight, 1.0F, 1.0F, 1, 0);
        vertex(vertexConsumer, matrix4f, matrix3f, pPackedLight, 0.0F, 1.0F, 0, 0);
        pPoseStack.popPose();

        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    private static void vertex(VertexConsumer pVertexConsumer, Matrix4f pMatrix4f, Matrix3f pMatrix3f, int pPackedLight, float pVertX, float pVertY, int pUVx, int pUVy){
        pVertexConsumer.vertex(pMatrix4f, pVertX - 0.5F, pVertY - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float)pUVx, (float)pUVy)
                .overlayCoords(OverlayTexture.NO_OVERLAY).uv2(pPackedLight).normal(pMatrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    @Override
    public ResourceLocation getTextureLocation(MagicMissileProjectile pEntity) {
        return TEXTURE_LOCATION;
    }
}
