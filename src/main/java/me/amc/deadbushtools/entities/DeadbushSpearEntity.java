package me.amc.deadbushtools.entities;

import me.amc.deadbushtools.items.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class DeadbushSpearEntity extends AbstractArrowEntity {
     private static DataParameter<Byte> LOYALTY_LEVEL = EntityDataManager.createKey(DeadbushSpearEntity.class, DataSerializers.BYTE);
     private static DataParameter<Boolean> FOIL_LEVEL = EntityDataManager.createKey(DeadbushSpearEntity.class, DataSerializers.BOOLEAN);
     private ItemStack spearItem;
     private boolean dealtDamage;
     public int returningTicks;
     private float damage;

     public DeadbushSpearEntity(EntityType<? extends DeadbushSpearEntity> entityType, World worldIn) {
          super(entityType, worldIn);
          this.spearItem = new ItemStack((IItemProvider) ModItems.DEADBUSH_SPEAR.get());
     }

     public DeadbushSpearEntity(World worldIn, LivingEntity entity, ItemStack stack, float damage) {
          super(ModEntityTypes.DEADBUSH_SPEAR, entity, worldIn);
          this.damage = damage + 5.0F;
          this.spearItem = new ItemStack((IItemProvider) ModItems.DEADBUSH_SPEAR.get());
          this.spearItem = stack.copy();
          this.dataManager.set(LOYALTY_LEVEL, Byte.valueOf((byte) EnchantmentHelper.getLoyaltyModifier(stack)));
          this.dataManager.set(FOIL_LEVEL, Boolean.valueOf(stack.hasEffect()));
     }

     @Override
     public IPacket<?> createSpawnPacket() {
          return NetworkHooks.getEntitySpawningPacket((Entity)this);
     }

     @Override
     protected void registerData() {
          super.registerData();
          this.dataManager.register(LOYALTY_LEVEL, (byte)0);
          this.dataManager.register(FOIL_LEVEL, false);
     }

     @Override
     public void tick() {
          if (this.timeInGround > 4)
               this.dealtDamage = true;
          Entity entity = this.func_234616_v_();
          if ((this.dealtDamage || this.getNoClip()) && entity != null) {
               int loyalty = ((Byte)this.dataManager.get(LOYALTY_LEVEL)).byteValue();
               if (loyalty > 0 && !shouldReturnToThrower()) {
                    if (!this.world.isRemote && this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED)
                         this.entityDropItem(this.getArrowStack(), 0.1F);
                    this.remove();
               } else if (loyalty > 0) {
                    this.setNoClip(true);
                    Vector3d vector3d = new Vector3d(entity.getPosX() - this.getPosX(), entity.getPosYEye() - this.getPosY(), entity.getPosZ() - this.getPosZ());
                    this.setRawPosition(this.getPosX(), this.getPosY() + vector3d.y * 0.015D * (double)loyalty, this.getPosZ());
                    if (this.world.isRemote) {
                         this.lastTickPosY = this.getPosY();
                    }

                    double d0 = 0.05D * (double)loyalty;
                    this.setMotion(this.getMotion().scale(0.95D).add(vector3d.normalize().scale(d0)));
                    if (this.returningTicks == 0) {
                         this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
                    }

                    ++this.returningTicks;
               }
          }
          super.tick();
     }

     private boolean shouldReturnToThrower() {
          Entity entity = this.func_234616_v_();
          if (entity != null && entity.isAlive()) {
               return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
          } else {
               return false;
          }
     }

     @Override
     protected ItemStack getArrowStack() {
          return this.spearItem.copy();
     }

     @OnlyIn(Dist.CLIENT)
     public boolean func_226572_w_() {
          return this.dataManager.get(FOIL_LEVEL);
     }

     @Nullable
     protected EntityRayTraceResult rayTraceEntities(Vector3d startVec, Vector3d endVec) {
          return this.dealtDamage ? null : super.rayTraceEntities(startVec, endVec);
     }

     @Override
     protected void onEntityHit(EntityRayTraceResult result) {
          Entity entity1 = result.getEntity();
          float thrownDamage = this.damage / 2.0F;
          if (entity1 instanceof LivingEntity) {
               LivingEntity entity2 = (LivingEntity)entity1;
               thrownDamage += EnchantmentHelper.getModifierForCreature(this.spearItem, entity2.getCreatureAttribute());
          }
          Entity owner = this.func_234616_v_();
          DamageSource source = DamageSource.causeTridentDamage((Entity)this, (owner == null) ? (Entity)this : owner);
          this.dealtDamage = true;
          SoundEvent soundEvent = SoundEvents.ITEM_TRIDENT_HIT;
          if (entity1.attackEntityFrom(source, thrownDamage)) {
               if (entity1.getType() == EntityType.ENDERMAN)
                    return;
               if (entity1 instanceof LivingEntity) {
                    LivingEntity entity2 = (LivingEntity)entity1;
                    if (owner instanceof LivingEntity) {
                         EnchantmentHelper.applyThornEnchantments(entity2, owner);
                         EnchantmentHelper.applyArthropodEnchantments((LivingEntity)owner, (Entity)entity2);
                    }
                    this.arrowHit(entity2);
               }
          }
          this.setMotion(this.getMotion().mul(-0.01D, -0.1D, -0.01D));
          float vol = 1.0F;
          if (this.world instanceof net.minecraft.world.server.ServerWorld && this.world.isThundering() && EnchantmentHelper.hasChanneling(this.spearItem)) {
               BlockPos position = entity1.getPosition();
               if (this.world.canSeeSky(position)) {
                    LightningBoltEntity lightning = (LightningBoltEntity)EntityType.LIGHTNING_BOLT.create(this.world);
                    lightning.moveForced(Vector3d.copyCenteredHorizontally((Vector3i)position));
                    lightning.setCaster((owner instanceof ServerPlayerEntity) ? (ServerPlayerEntity)owner : null);
                    this.world.addEntity((Entity)lightning);
                    soundEvent = SoundEvents.ITEM_TRIDENT_THUNDER;
                    vol = 5.0F;
               }
          }
          this.playSound(soundEvent, vol, 1.0F);
     }

     @Override
     protected SoundEvent getHitEntitySound() {
          return SoundEvents.ITEM_TRIDENT_HIT_GROUND;
     }

     @Override
     public void onCollideWithPlayer(PlayerEntity entityIn) {
          Entity entity = this.func_234616_v_();
          if (entity == null || entity.getUniqueID() == entityIn.getUniqueID()) {
               super.onCollideWithPlayer(entityIn);
          }
     }

     @Override
     public void readAdditional(CompoundNBT compound) {
          super.readAdditional(compound);
          if (compound.contains("Spear", 10)) {
               this.spearItem = ItemStack.read(compound.getCompound("Spear"));
          }
          this.dealtDamage = compound.getBoolean("DealtDamage");
          this.dataManager.set(LOYALTY_LEVEL, (byte)EnchantmentHelper.getLoyaltyModifier(this.spearItem));
     }

     @Override
     public void writeAdditional(CompoundNBT compound) {
          super.writeAdditional(compound);
          compound.put("Spear", this.spearItem.write(new CompoundNBT()));
          compound.putBoolean("DealtDamage", this.dealtDamage);
     }

     @Override
     public void func_225516_i_() {
          int i = this.dataManager.get(LOYALTY_LEVEL);
          if (this.pickupStatus != AbstractArrowEntity.PickupStatus.ALLOWED || i <= 0) {
               super.func_225516_i_();
          }
     }

     protected float getWaterDrag() {
          return 0.99F;
     }

     @OnlyIn(Dist.CLIENT)
     public boolean isInRangeToRender3d(double x, double y, double z) {
          return true;
     }
}
