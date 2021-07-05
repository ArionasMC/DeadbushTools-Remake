package me.amc.deadbushtools.items;

import me.amc.deadbushtools.DeadbushToolsMod;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum DeadbushArmorMaterial implements IArmorMaterial {

     DEADBUSH(1000, new int[]{3, 8, 6, 3}, 5, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
             Ingredient.fromStacks(new ItemStack(Items.DEAD_BUSH)),
             DeadbushToolsMod.MOD_ID + ":deadbush", 3, 3f);

     private final int durability;
     private final int[] damageReductionAmountArray;
     private final int enchantability;
     private final SoundEvent soundEvent;
     private final Ingredient repairMaterial;
     private final String name;
     private final float toughness;
     private final float knockbackResistance;

     DeadbushArmorMaterial(int durability, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent,
                           Ingredient repairMaterial, String name, float toughness, float knockbackResistance) {
          this.durability = durability;
          this.damageReductionAmountArray = damageReductionAmountArray;
          this.enchantability = enchantability;
          this.soundEvent = soundEvent;
          this.repairMaterial = repairMaterial;
          this.name = name;
          this.toughness = toughness;
          this.knockbackResistance = knockbackResistance;
     }

     @Override
     public int getDurability(EquipmentSlotType slotIn) {
          return this.durability;
     }

     @Override
     public int getDamageReductionAmount(EquipmentSlotType slotIn) {
          return damageReductionAmountArray[slotIn.getIndex()];
     }

     @Override
     public int getEnchantability() {
          return this.enchantability;
     }

     @Override
     public SoundEvent getSoundEvent() {
          return this.soundEvent;
     }

     @Override
     public Ingredient getRepairMaterial() {
          return this.repairMaterial;
     }

     @Override
     public String getName() {
          return this.name;
     }

     @Override
     public float getToughness() {
          return this.toughness;
     }

     @Override
     public float getKnockbackResistance() {
          return this.knockbackResistance;
     }

}
