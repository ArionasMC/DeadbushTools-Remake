package me.amc.deadbushtools.entities.renderers;

import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.entities.DeadbushMobEntity;
import me.amc.deadbushtools.entities.models.DeadbushMobModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class DeadbushMobRenderer extends MobRenderer<DeadbushMobEntity, DeadbushMobModel<DeadbushMobEntity>> {
     protected static final ResourceLocation TEXTURE = new ResourceLocation(DeadbushToolsMod.MOD_ID,
             "textures/entity/deadbush_mob.png");

     public DeadbushMobRenderer(EntityRendererManager renderManagerIn)
     {
          super(renderManagerIn, new DeadbushMobModel<>(), 0.9f);
     }

     @Override
     public ResourceLocation getEntityTexture(DeadbushMobEntity entity)
     {
          return TEXTURE;
     }
}
