package me.amc.deadbushtools.entities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.entities.DeadbushSpearEntity;
import me.amc.deadbushtools.entities.models.DeadbushSpearModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class DeadbushSpearRenderer extends EntityRenderer<DeadbushSpearEntity> implements IEntityRenderer<DeadbushSpearEntity, DeadbushSpearModel> {
     public static ResourceLocation SPEAR_TEXTURE = new ResourceLocation(DeadbushToolsMod.MOD_ID, "/textures/entity/deadbush_spear.png");
     private DeadbushSpearModel model;

     public DeadbushSpearRenderer(EntityRendererManager renderManager) {
          super(renderManager);
          this.model = new DeadbushSpearModel();
     }

     @Override
     public void render(DeadbushSpearEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
          matrixStack.push();
          matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90.0F));
          matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch) + 90.0F));
          IVertexBuilder verBuilder = ItemRenderer.getEntityGlintVertexBuilder(buffer, this.model.getRenderType(getEntityTexture(entity)), false, entity.func_226572_w_());
          this.model.render(matrixStack, verBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
          matrixStack.pop();
          super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
     }

     public DeadbushSpearModel getEntityModel() {
          return this.model;
     }

     public ResourceLocation getEntityTexture(DeadbushSpearEntity entity) {
          return SPEAR_TEXTURE;
     }
}
