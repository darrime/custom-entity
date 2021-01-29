package com.darrime.main;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Zombie;


import java.lang.reflect.Field;

import static com.darrime.main.CustomZombie.addCustomEntity;

public class Main extends JavaPlugin implements Listener{


    @Override
    public void onEnable() {
        addCustomEntity(CustomZombie.class, "CustomZombie", 51);


    }


    public void spawnCustomEntity(Location loc)
    {
        net.minecraft.server.v1_16_R3.World nmsWorld = ((CraftWorld) loc.getWorld()).getHandle();
        CustomZombie customZombie = new CustomZombie(nmsWorld);
        customZombie.setPosition(loc.getX(), loc.getY(), loc.getZ());
        nmsWorld.addEntity(customZombie);

    }











    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
