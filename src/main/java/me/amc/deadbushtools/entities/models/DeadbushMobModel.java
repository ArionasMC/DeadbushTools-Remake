package me.amc.deadbushtools.entities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.amc.deadbushtools.entities.DeadbushMobEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.HumanoidHeadModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class DeadbushMobModel<T extends DeadbushMobEntity> extends EntityModel<T> {

     private final ModelRenderer head;
     private final ModelRenderer body;
     private final ModelRenderer left_arm;
     private final ModelRenderer right_arm;
     private final ModelRenderer left_leg;
     private final ModelRenderer right_leg;

     public DeadbushMobModel() {
          textureWidth = 64;
          textureHeight = 64;

          head = new ModelRenderer(this);
          head.setRotationPoint(0.0F, 0.0F, 2.0F);
          head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

          body = new ModelRenderer(this);
          body.setRotationPoint(0.0F, 12.0F, 2.0F);
          body.setTextureOffset(16, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

          left_arm = new ModelRenderer(this);
          left_arm.setRotationPoint(4.0F, 0.0F, 2.0F);
          left_arm.setTextureOffset(40, 16).addBox(0.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

          right_arm = new ModelRenderer(this);
          right_arm.setRotationPoint(-6.0F, 0.0F, 2.0F);
          right_arm.setTextureOffset(40, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

          left_leg = new ModelRenderer(this);
          left_leg.setRotationPoint(2.0F, 12.0F, 2.0F);
          left_leg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

          right_leg = new ModelRenderer(this);
          right_leg.setRotationPoint(-2.0F, 12.0F, 2.0F);
          right_leg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

     }

     @Override
     public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
          //previously the render function, render code was moved to a method below
          //super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
          this.head.rotateAngleY = (float) Math.toRadians(netHeadYaw);
          this.head.rotateAngleX = (float) Math.toRadians(headPitch);
          this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
          this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
          this.right_leg.rotateAngleY = 0.0F;
          this.left_leg.rotateAngleY = 0.0F;

          this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.2F;
          this.right_arm.rotateAngleY = 0.0F;
          this.right_arm.rotateAngleZ = 0.0F;
          this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.2F;
          this.left_arm.rotateAngleY = 0.0F;
          this.left_arm.rotateAngleZ = 0.0F;

          this.right_arm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
          this.left_arm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
          this.right_arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
          this.left_arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
          /*
          right_arm.copyModelAngles(right_arm);
          left_arm.copyModelAngles(left_arm);
          right_leg.copyModelAngles(right_leg);
          left_leg.copyModelAngles(left_leg);
          body.copyModelAngles(body);

           */
          /*
          head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
          head.rotateAngleX = headPitch / (180F / (float) Math.PI);
          right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 2.0F * limbSwingAmount * 0.5F;
          left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
          right_arm.rotateAngleZ = 0.0F;
          left_arm.rotateAngleZ = 0.0F;
          right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
          left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
          right_leg.rotateAngleY = 0.0F;
          left_leg.rotateAngleY = 0.0F;
          right_arm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
          left_arm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
          right_arm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
          left_arm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;

           */
     }

     @Override
     public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
          head.render(matrixStack, buffer, packedLight, packedOverlay);
          body.render(matrixStack, buffer, packedLight, packedOverlay);
          left_arm.render(matrixStack, buffer, packedLight, packedOverlay);
          right_arm.render(matrixStack, buffer, packedLight, packedOverlay);
          left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
          right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
     }

     public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
          modelRenderer.rotateAngleX = x;
          modelRenderer.rotateAngleY = y;
          modelRenderer.rotateAngleZ = z;
     }

}
