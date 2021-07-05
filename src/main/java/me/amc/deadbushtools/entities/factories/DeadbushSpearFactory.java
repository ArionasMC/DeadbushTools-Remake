package me.amc.deadbushtools.entities.factories;

import me.amc.deadbushtools.entities.DeadbushSpearEntity;
import me.amc.deadbushtools.entities.renderers.DeadbushSpearRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DeadbushSpearFactory implements IRenderFactory<DeadbushSpearEntity> {

     public static final DeadbushSpearFactory INSTANCE = new DeadbushSpearFactory();
     private DeadbushSpearRenderer spearRenderer;

     public EntityRenderer<? super DeadbushSpearEntity> createRenderFor(EntityRendererManager manager) {
          this.spearRenderer = new DeadbushSpearRenderer(manager);
          return this.spearRenderer;
     }
}
