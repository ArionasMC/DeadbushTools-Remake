package me.amc.deadbushtools.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public enum DeadbushItemTier implements IItemTier {
     DEADBUSH(5, 1000, 50f, 10f, 30,
             Ingredient.fromStacks(new ItemStack(Items.DEAD_BUSH)));

     private final int harvestLevel;
     private final int maxUses;
     private final float efficiency;
     private final float attackDamage;
     private final int enchantability;
     private final Ingredient repairMaterial;

     DeadbushItemTier(int harvestLevel, int maxUses, float efficiency,
                      float attackDamage, int enchantability, Ingredient repairMaterial) {
          this.harvestLevel = harvestLevel;
          this.maxUses = maxUses;
          this.efficiency = efficiency;
          this.attackDamage = attackDamage;
          this.enchantability = enchantability;
          this.repairMaterial = repairMaterial;
     }

     @Override
     public int getMaxUses()
     {
          return maxUses;
     }

     @Override
     public float getEfficiency()
     {
          return efficiency;
     }

     @Override
     public float getAttackDamage()
     {
          return attackDamage;
     }

     @Override
     public int getHarvestLevel()
     {
          return harvestLevel;
     }

     @Override
     public int getEnchantability()
     {
          return enchantability;
     }

     @Override
     public Ingredient getRepairMaterial()
     {
          return repairMaterial;
     }

}
