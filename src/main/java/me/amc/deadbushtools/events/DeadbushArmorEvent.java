package me.amc.deadbushtools.events;

import me.amc.deadbushtools.DeadbushToolsMod;
import me.amc.deadbushtools.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DeadbushArmorEvent {

     private int maxTick = 3;
     private int tick = 0;

     @SubscribeEvent
     public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
          if (event.getEntityLiving() != null && event.getEntityLiving() instanceof PlayerEntity) {
               PlayerEntity player = (PlayerEntity) event.getEntityLiving();
               World world = event.getEntityLiving().getEntityWorld();
               tick++;
               if (tick >= maxTick && fullSet(player)) {
                    BlockPos blockPos = player.getPosition();
                    blockPos = blockPos.add(0, -1, 0);
                    BlockState state = world.getBlockState(blockPos);

                    if (state.getBlock() instanceof GrassBlock || state.getBlock() == Blocks.DIRT) {
                         world.setBlockState(blockPos, Blocks.SAND.getDefaultState(), 1);
                         DeadbushToolsMod.LOGGER.info(blockPos.toString() + ", " + state.toString());
                    }
                    tick = 0;
               }
          }
     }

     private boolean fullSet(PlayerEntity player) {
          return (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ModItems.DEADBUSH_BOOTS.get())
                  && (player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ModItems.DEADBUSH_LEGGINGS.get())
                  && (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ModItems.DEADBUSH_CHESTPLATE.get())
                  && (player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ModItems.DEADBUSH_HELMET.get());

     }

}
