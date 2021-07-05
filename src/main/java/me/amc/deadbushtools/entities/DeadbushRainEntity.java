package me.amc.deadbushtools.entities;

import me.amc.deadbushtools.items.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class DeadbushRainEntity extends ProjectileItemEntity {

     public DeadbushRainEntity(EntityType<? extends DeadbushRainEntity> p_i50154_1_, World p_i50154_2_) {
          super(p_i50154_1_, p_i50154_2_);
     }

     public DeadbushRainEntity(World worldIn, LivingEntity throwerIn) {
          super(ModEntityTypes.DEADBUSH_RAIN.get(), throwerIn, worldIn);
     }

     public DeadbushRainEntity(World worldIn, double x, double y, double z) {
          super(ModEntityTypes.DEADBUSH_RAIN.get(), x, y, z, worldIn);
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
               double y = this.getPosY() + 7;
               double z = this.getPosZ();

               World worldObj = this.world;

               for(int j = 0; j < 5+rand.nextInt(8); j++) {
                    for(int i = 0; i < 5+rand.nextInt(6); i++) {
                         x++;
                         worldObj.setBlockState(new BlockPos(x,y,z+j), Blocks.SAND.getDefaultState());
                         worldObj.setBlockState(new BlockPos(x,y+2,z+j), Blocks.DEAD_BUSH.getDefaultState());
                         worldObj.destroyBlock(new BlockPos(x,y+2,z+j), true);
                         ItemEntity entity = new ItemEntity(worldObj, x, y+2, z+j, new ItemStack(Item.getItemFromBlock(Blocks.DEAD_BUSH)));
                         worldObj.addEntity(entity);
                    }

                    for(int i = 0; i < 5+rand.nextInt(6); i++) {
                         x--;
                         worldObj.setBlockState(new BlockPos(x,y,z-j), Blocks.SAND.getDefaultState());
                         worldObj.setBlockState(new BlockPos(x,y+2,z-j), Blocks.DEAD_BUSH.getDefaultState());
                         worldObj.destroyBlock(new BlockPos(x,y+2,z-j), true);
                         ItemEntity entity = new ItemEntity(worldObj, x, y+2, z-j, new ItemStack(Item.getItemFromBlock(Blocks.DEAD_BUSH)));
                         worldObj.addEntity(entity);
                    }
               }

               this.remove();
          }
     }

     @Override
     protected float getGravityVelocity() {
          return 0.1F;
     }

     @Override
     protected Item getDefaultItem() {
          return ModItems.DEADBUSH_RAIN.get();
     }
}
