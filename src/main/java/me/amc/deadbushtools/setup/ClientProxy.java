package me.amc.deadbushtools.setup;

import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.entities.DeadbushBombEntity;
import me.amc.deadbushtools.entities.DeadbushRainEntity;
import me.amc.deadbushtools.entities.ModEntityTypes;
import me.amc.deadbushtools.entities.factories.DeadbushSpearFactory;
import me.amc.deadbushtools.entities.renderers.DeadbushMobRenderer;
import me.amc.deadbushtools.items.DeadbushToolsSpawnEggItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeadbushToolsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy {
     @Override
     public void init() {

          DeadbushToolsMod.LOGGER.info("Hello from ClientProxy!");

          RenderingRegistry.registerEntityRenderingHandler(
                  ModEntityTypes.DEADBUSH_RAIN.get(), new DeadbushRainRenderFactory());

          RenderingRegistry.registerEntityRenderingHandler(
                  ModEntityTypes.DEADBUSH_BOMB.get(), new DeadbushBombRenderFactory());

          RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DEADBUSH_SPEAR, (IRenderFactory) DeadbushSpearFactory.INSTANCE);

          RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DEADBUSH_MOB.get(),
                  DeadbushMobRenderer::new);

          DeadbushToolsSpawnEggItem.initSpawnEggs();

     }

     @Override
     public World getClientWorld() {
          return Minecraft.getInstance().world;
     }

     private static class DeadbushRainRenderFactory implements IRenderFactory<DeadbushRainEntity> {
          @Override
          public EntityRenderer<? super DeadbushRainEntity> createRenderFor(EntityRendererManager manager) {
               ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
               return new SpriteRenderer<>(manager, itemRenderer);
          }
     }

     private static class DeadbushBombRenderFactory implements IRenderFactory<DeadbushBombEntity> {
          @Override
          public EntityRenderer<? super DeadbushBombEntity> createRenderFor(EntityRendererManager manager) {
               ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
               return new SpriteRenderer<>(manager, itemRenderer);
          }
     }

}
