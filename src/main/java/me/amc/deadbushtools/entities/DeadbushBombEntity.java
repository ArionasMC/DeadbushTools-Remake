package me.amc.deadbushtools.entities;

import me.amc.deadbushtools.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class DeadbushBombEntity extends ProjectileItemEntity {

     public DeadbushBombEntity(EntityType<? extends DeadbushBombEntity> p_i50154_1_, World p_i50154_2_) {
          super(p_i50154_1_, p_i50154_2_);
     }

     public DeadbushBombEntity(World worldIn, LivingEntity throwerIn) {
          super(ModEntityTypes.DEADBUSH_BOMB.get(), throwerIn, worldIn);
     }

     public DeadbushBombEntity(World worldIn, double x, double y, double z) {
          super(ModEntityTypes.DEADBUSH_BOMB.get(), x, y, z, worldIn);
     }

     @Nonnull
     @Override
     public IPacket<?> createSpawnPacket() {
          return NetworkHooks.getEntitySpawningPacket(this);
     }

     @Override
     public void onImpact(RayTraceResult result) {
          super.onImpact(result);
          if(!this.world.isRemote) {
               double x = this.getPosX();
               double y = this.getPosY();
               double z = this.getPosZ();

               World worldObj = this.world;

               worldObj.createExplosion(this, x, y, z, 5+this.rand.nextInt(6), true, Explosion.Mode.DESTROY);

               this.remove();
          }
     }

     @Override
     protected float getGravityVelocity() {
          return 0.1F;
     }

     @Override
     protected Item getDefaultItem() {
          return ModItems.DEADBUSH_BOMB.get();
     }
}
