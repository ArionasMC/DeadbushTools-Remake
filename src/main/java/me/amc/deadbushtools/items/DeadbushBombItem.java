package me.amc.deadbushtools.items;

import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.entities.DeadbushBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class DeadbushBombItem extends Item {

     public DeadbushBombItem() {
          super(new Item.Properties().group(DeadbushToolsMod.DEADBUSH_TAB));
     }

     @Override
     public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
          ItemStack itemStack = playerIn.getHeldItem(handIn);
          if(!worldIn.isRemote) {
               DeadbushBombEntity deadbushBomb = new DeadbushBombEntity(worldIn, playerIn);
               deadbushBomb.setItem(itemStack);
               deadbushBomb.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
               worldIn.addEntity(deadbushBomb);
          }
          return ActionResult.func_233538_a_(itemStack, worldIn.isRemote());
     }

}
