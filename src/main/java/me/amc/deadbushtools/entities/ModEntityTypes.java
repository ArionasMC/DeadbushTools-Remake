package me.amc.deadbushtools.entities;

import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.util.Registration;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntityTypes {

     public static final RegistryObject<EntityType<DeadbushRainEntity>> DEADBUSH_RAIN = Registration.ENTITY_TYPES.register("deadbush_rain",
             () -> EntityType.Builder.<DeadbushRainEntity>create(DeadbushRainEntity::new, EntityClassification.MISC)
                     .size(0.25f, 0.25f)
                     .build(new ResourceLocation(DeadbushToolsMod.MOD_ID+"deadbush_rain").toString()));

     public static final RegistryObject<EntityType<DeadbushBombEntity>> DEADBUSH_BOMB = Registration.ENTITY_TYPES.register("deadbush_bomb",
             () -> EntityType.Builder.<DeadbushBombEntity>create(DeadbushBombEntity::new, EntityClassification.MISC)
                     .size(0.25f, 0.25f)
                     .build(new ResourceLocation(DeadbushToolsMod.MOD_ID+"deadbush_bomb").toString()));

     public static final RegistryObject<EntityType<DeadbushMobEntity>> DEADBUSH_MOB = Registration.ENTITY_TYPES.register("deadbush_mob",
             () -> EntityType.Builder.create(DeadbushMobEntity::new, EntityClassification.CREATURE)
                     .size(1.5f, 1.5f)
                     .build(new ResourceLocation(DeadbushToolsMod.MOD_ID + "deadbush_mob").toString()));

     public static final EntityType<DeadbushSpearEntity> DEADBUSH_SPEAR = create("deadbush_spear", DeadbushSpearEntity::new, EntityClassification.MISC, 1.0F, 1.0F);

     public static void register() {}

     private static <T extends net.minecraft.entity.Entity> EntityType<T> create(String name, EntityType.IFactory<T> factory, EntityClassification classification, float width, float height) {
          EntityType<T> type = EntityType.Builder.create(factory, classification).size(width,height).setTrackingRange(128).build(name);
          Registration.ENTITY_TYPES.register(name, () -> type);
          return type;
     }
}
