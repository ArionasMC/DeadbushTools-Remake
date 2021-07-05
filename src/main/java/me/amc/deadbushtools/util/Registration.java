package me.amc.deadbushtools.util;

import me.amc.deadbushtools.DeadbushToolsMod;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

     public static final DeferredRegister<Item> ITEMS
             = DeferredRegister.create(ForgeRegistries.ITEMS, DeadbushToolsMod.MOD_ID);

     public static DeferredRegister<EntityType<?>> ENTITY_TYPES
             = DeferredRegister.create(ForgeRegistries.ENTITIES, DeadbushToolsMod.MOD_ID);

     public static void init() {
          IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
          ITEMS.register(eventBus);
          ENTITY_TYPES.register(eventBus);
     }

}
