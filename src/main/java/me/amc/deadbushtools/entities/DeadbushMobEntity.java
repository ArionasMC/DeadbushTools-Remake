package me.amc.deadbushtools.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;

public class DeadbushMobEntity extends AnimalEntity
{
     protected DeadbushMobEntity(EntityType<? extends AnimalEntity> type, World worldIn)
     {
          super(type, worldIn);
     }

     public static AttributeModifierMap.MutableAttribute setCustomAttribute()
     {
          return MobEntity.func_233666_p_()
                  .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                  .createMutableAttribute(Attributes.MOVEMENT_SPEED, 3.0D)
                  .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D);

     }

     @Override
     protected int getExperiencePoints(PlayerEntity player)
     {
          return 10 + this.world.rand.nextInt(15);
     }

     @Override
     protected SoundEvent getAmbientSound()
     {
          return SoundEvents.ENTITY_ZOMBIE_AMBIENT;
     }

     @Override
     protected SoundEvent getDeathSound()
     {
          return SoundEvents.ENTITY_ZOMBIE_DEATH;
     }

     @Override
     protected SoundEvent getHurtSound(DamageSource source)
     {
          return SoundEvents.ENTITY_ZOMBIE_HURT;
     }

     @Override
     protected void playStepSound(BlockPos pos, BlockState blockIn)
     {
          this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.75f, 0.25f);
     }

     @Override
     protected void registerGoals()
     {
          super.registerGoals();
          this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1D, false));
          this.goalSelector.addGoal(0, new SwimGoal(this));
          this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
          this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
          this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
          this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
          this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
          this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
          this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
     }

     @Nullable
     @Override
     public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_)
     {
          return ModEntityTypes.DEADBUSH_MOB.get().create(this.world);
     }
}
