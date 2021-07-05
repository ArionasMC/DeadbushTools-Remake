package me.amc.deadbushtools.items;

import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.entities.ModEntityTypes;
import me.amc.deadbushtools.util.Registration;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

     // Tools
     public static final RegistryObject<SwordItem> DEADBUSH_SWORD =
             Registration.ITEMS.register("deadbush_sword",
                     () -> new SwordItem(DeadbushItemTier.DEADBUSH, 25, 4f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<SwordItem> DEADBUSH_KATANA =
             Registration.ITEMS.register("deadbush_katana",
                     () -> new SwordItem(DeadbushItemTier.DEADBUSH, 20, 8f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<SwordItem> DEADBUSH_KNIFE =
             Registration.ITEMS.register("deadbush_knife",
                     () -> new SwordItem(DeadbushItemTier.DEADBUSH, 10, 5f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<SwordItem> DEADBUSH_NUNCHAKU =
             Registration.ITEMS.register("deadbush_nunchaku",
                     () -> new SwordItem(DeadbushItemTier.DEADBUSH, 30, 10f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<SwordItem> DEADBUSH_BATTLE_AXE =
             Registration.ITEMS.register("deadbush_battle_axe",
                     ()->new SwordItem(DeadbushItemTier.DEADBUSH, 35, 0f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<AxeItem> DEADBUSH_AXE =
             Registration.ITEMS.register("deadbush_axe",
                     ()->new AxeItem(DeadbushItemTier.DEADBUSH, 10, 1f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<PickaxeItem> DEADBUSH_PICKAXE =
             Registration.ITEMS.register("deadbush_pickaxe",
                     ()->new PickaxeItem(DeadbushItemTier.DEADBUSH, 0, 0f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<ShovelItem> DEADBUSH_SHOVEL =
             Registration.ITEMS.register("deadbush_shovel",
                     ()->new ShovelItem(DeadbushItemTier.DEADBUSH, 0, 0f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<HoeItem> DEADBUSH_HOE =
             Registration.ITEMS.register("deadbush_hoe",
                     ()->new HoeItem(DeadbushItemTier.DEADBUSH, 0, 0f,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     // Throwable Items
     public static final RegistryObject<DeadbushRainItem> DEADBUSH_RAIN =
             Registration.ITEMS.register("deadbush_rain", ()->new DeadbushRainItem());

     public static final RegistryObject<DeadbushBombItem> DEADBUSH_BOMB =
             Registration.ITEMS.register("deadbush_bomb", ()->new DeadbushBombItem());

     public static final RegistryObject<DeadbushSpearItem> DEADBUSH_SPEAR =
             Registration.ITEMS.register("deadbush_spear",
                     () -> new DeadbushSpearItem(DeadbushItemTier.DEADBUSH, 10F, -2.9F,
                             (new Item.Properties()).group(DeadbushToolsMod.DEADBUSH_TAB)));

     // Armors
     public static final RegistryObject<Item> DEADBUSH_HELMET =
             Registration.ITEMS.register("deadbush_helmet",
                     () -> new ArmorItem(DeadbushArmorMaterial.DEADBUSH, EquipmentSlotType.HEAD,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<Item> DEADBUSH_CHESTPLATE =
             Registration.ITEMS.register("deadbush_chestplate",
                     () -> new ArmorItem(DeadbushArmorMaterial.DEADBUSH, EquipmentSlotType.CHEST,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<Item> DEADBUSH_LEGGINGS =
             Registration.ITEMS.register("deadbush_leggings",
                     () -> new ArmorItem(DeadbushArmorMaterial.DEADBUSH, EquipmentSlotType.LEGS,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     public static final RegistryObject<Item> DEADBUSH_BOOTS =
             Registration.ITEMS.register("deadbush_boots",
                     () -> new ArmorItem(DeadbushArmorMaterial.DEADBUSH, EquipmentSlotType.FEET,
                             new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB)));

     // Eggs
     public static final RegistryObject<DeadbushToolsSpawnEggItem> DEADBUSH_MOB_SPAWNING_EGG =
             Registration.ITEMS.register("deadbush_mob_spawn_egg",
                     () -> new DeadbushToolsSpawnEggItem(ModEntityTypes.DEADBUSH_MOB, 0xd8bb9d, 0xa63c1a,
                             new Item.Properties().group(ItemGroup.MISC)));

     public static void register() { }
}
