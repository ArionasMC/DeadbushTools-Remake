package me.amc.deadbushtools.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import me.amc.deadbushtools.entities.DeadbushSpearEntity;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class DeadbushSpearItem extends TieredItem {
     private final float damage;

     private final float speed;

     public DeadbushSpearItem(IItemTier tier, float damage, float speed, Item.Properties itemGroup) {
          super(tier, itemGroup);
          this.damage = tier.getAttackDamage()+damage;
          this.speed = speed;
     }

     @Override
     public int getItemEnchantability() {
          return 1;
     }

     @Override
     public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
          return !player.isCreative();
     }

     @Override
     public UseAction getUseAction(ItemStack stack) {
          return UseAction.SPEAR;
     }

     @Override
     public int getUseDuration(ItemStack stack) {
          return 72000;
     }

     @Override
     public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entity, int time) {
          if (!(entity instanceof PlayerEntity))
               return;
          PlayerEntity player = (PlayerEntity)entity;
          int time2 = getUseDuration(stack) - time;
          if (time2 < 10)
               return;
          int riptide = EnchantmentHelper.getRiptideModifier(stack);
          if (riptide > 0 && !player.isWet())
               return;
          if (!worldIn.isRemote) {
               stack.damageItem(1, (LivingEntity)player, playerEntity1 -> player.sendBreakAnimation(entity.getActiveHand()));
               if (riptide == 0) {
                    DeadbushSpearEntity tridentEntity = new DeadbushSpearEntity(worldIn, (LivingEntity)player, stack, this.damage);
                    tridentEntity.func_234612_a_((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F + riptide * 0.5F, 1.0F);
                    if (player.abilities.isCreativeMode)
                         tridentEntity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                    worldIn.addEntity((Entity)tridentEntity);
                    worldIn.playMovingSound(null, (Entity)tridentEntity, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    if (!player.abilities.isCreativeMode)
                         player.inventory.deleteStack(stack);
               }
          }
          player.addStat(Stats.ITEM_USED.get(this));
          if (riptide > 0) {
               SoundEvent soundEvent;
               float rotationY = player.rotationYaw;
               float rotationX = player.rotationPitch;
               float math1 = -MathHelper.sin(rotationY * 0.017453292F) * MathHelper.cos(rotationX * 0.017453292F);
               float math2 = -MathHelper.sin(rotationX * 0.017453292F);
               float math3 = MathHelper.cos(rotationY * 0.017453292F) * MathHelper.cos(rotationX * 0.017453292F);
               float math4 = MathHelper.sqrt(math1 * math1 + math2 * math2 + math3 * math3);
               float math5 = 3.0F * (1.0F + riptide) / 4.0F;
               math1 *= math5 / math4;
               math2 *= math5 / math4;
               math3 *= math5 / math4;
               player.addVelocity(math1, math2, math3);
               player.startSpinAttack(20);
               if (player.isOnGround()) {
                    float math6 = 1.1999999F;
                    player.move(MoverType.SELF, new Vector3d(0.0D, 1.1999999284744263D, 0.0D));
               }
               if (riptide >= 3) {
                    soundEvent = SoundEvents.ITEM_TRIDENT_RIPTIDE_3;
               } else if (riptide == 2) {
                    soundEvent = SoundEvents.ITEM_TRIDENT_RIPTIDE_2;
               } else {
                    soundEvent = SoundEvents.ITEM_TRIDENT_RIPTIDE_1;
               }
               worldIn.playMovingSound(null, (Entity)player, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
          }
     }

     @Override
     public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
          ItemStack itemstack = playerIn.getHeldItem(handIn);
          if (itemstack.getDamage() >= itemstack.getMaxDamage() - 1) {
               return ActionResult.resultFail(itemstack);
          } else if (EnchantmentHelper.getRiptideModifier(itemstack) > 0 && !playerIn.isWet()) {
               return ActionResult.resultFail(itemstack);
          } else {
               playerIn.setActiveHand(handIn);
               return ActionResult.resultConsume(itemstack);
          }
     }

     @Override
     public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
          stack.damageItem(1, attacker, (entity) -> {
               entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
          });
          return true;
     }

     @Override
     public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
          if ((double)state.getBlockHardness(worldIn, pos) != 0.0D) {
               stack.damageItem(2, entityLiving, (entity) -> {
                    entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
               });
          }
          return true;
     }

     @Override
     public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType eqSlot) {
          HashMultimap hashMultimap = HashMultimap.create();
          if (eqSlot == EquipmentSlotType.MAINHAND) {
               hashMultimap.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.speed, AttributeModifier.Operation.ADDITION));
               hashMultimap.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.damage, AttributeModifier.Operation.ADDITION));
          }
          return (Multimap<Attribute, AttributeModifier>)hashMultimap;
     }
}
