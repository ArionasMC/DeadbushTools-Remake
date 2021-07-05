package me.amc.deadbushtools.entities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.entities.DeadbushSpearEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;

public class DeadbushSpearModel extends EntityModel<DeadbushSpearEntity> {

     public static ResourceLocation TEXTURE = new ResourceLocation(DeadbushToolsMod.MOD_ID, "textures/entity/deadbush_spear.png");

     private final ModelRenderer bb_main;
     private final ModelRenderer top2_r1;
     private final ModelRenderer top1_r1;
     private final ModelRenderer bottom2_r1;
     private final ModelRenderer bottom1_r1;

     public DeadbushSpearModel() {
          textureWidth = 32;
          textureHeight = 32;

          bb_main = new ModelRenderer(this);
          bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
          bb_main.setTextureOffset(0, 0).addBox(-1.0F, -30.0F, -1.0F, 2.0F, 30.0F, 2.0F, 0.0F, false);
          bb_main.setTextureOffset(8, 0).addBox(-1.0F, -35.0F, -2.0F, 2.0F, 5.0F, 4.0F, 0.0F, false);
          bb_main.setTextureOffset(20, 3).addBox(-2.0F, -33.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);
          bb_main.setTextureOffset(20, 3).addBox(1.0F, -33.0F, -2.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

          top2_r1 = new ModelRenderer(this);
          top2_r1.setRotationPoint(0.0F, -30.0F, 0.0F);
          bb_main.addChild(top2_r1);
          setRotationAngle(top2_r1, 0.0F, 0.0F, -0.4363F);
          top2_r1.setTextureOffset(18, 9).addBox(2.0F, -6.0F, -2.0F, 1.0F, 5.0F, 4.0F, 0.0F, false);

          top1_r1 = new ModelRenderer(this);
          top1_r1.setRotationPoint(0.0F, -30.0F, 0.0F);
          bb_main.addChild(top1_r1);
          setRotationAngle(top1_r1, 0.0F, 0.0F, 0.4363F);
          top1_r1.setTextureOffset(8, 9).addBox(-3.0F, -6.0F, -2.0F, 1.0F, 5.0F, 4.0F, 0.0F, false);

          bottom2_r1 = new ModelRenderer(this);
          bottom2_r1.setRotationPoint(0.0F, -30.0F, 0.0F);
          bb_main.addChild(bottom2_r1);
          setRotationAngle(bottom2_r1, 0.0F, 0.0F, 0.5672F);
          bottom2_r1.setTextureOffset(18, 18).addBox(0.0F, -3.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);

          bottom1_r1 = new ModelRenderer(this);
          bottom1_r1.setRotationPoint(0.0F, -30.0F, 0.0F);
          bb_main.addChild(bottom1_r1);
          setRotationAngle(bottom1_r1, 0.0F, 0.0F, -0.5672F);
          bottom1_r1.setTextureOffset(8, 18).addBox(-1.0F, -3.0F, -2.0F, 1.0F, 4.0F, 4.0F, 0.0F, false);
     }

     @Override
     public void setRotationAngles(DeadbushSpearEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
          //previously the render function, render code was moved to a method below
     }

     @Override
     public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
          bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
     }

     public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
          modelRenderer.rotateAngleX = x;
          modelRenderer.rotateAngleY = y;
          modelRenderer.rotateAngleZ = z;
     }
}
