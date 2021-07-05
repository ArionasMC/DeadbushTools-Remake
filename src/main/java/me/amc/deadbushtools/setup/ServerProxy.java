package me.amc.deadbushtools.setup;

import me.amc.deadbushtools.DeadbushToolsMod;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeadbushToolsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
public class ServerProxy implements IProxy {

     @Override
     public void init() { }

     @Override
     public World getClientWorld() {
          throw new IllegalStateException("Cannot access world from server side!");
     }
}
