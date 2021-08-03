package com.defiantburger.randhunt;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Randhunt extends JavaPlugin {

    private static Randhunt plugin;
    FileConfiguration config = getConfig();
    public static List<String> runners = new ArrayList<>();
    public static List<String> hunters = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        config.addDefault("secondsPerItem", 60);
        config.options().copyDefaults(true);
        saveConfig();

        initItemLoop((int) config.get("secondsPerItem"));
    }

    @Override
    public void onDisable() {
    }

    public static Randhunt getPlugin() {
        return plugin;
    }

    public void initItemLoop(int s) {
        Random random = new Random();
        Material[] materials = Material.values();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            Bukkit.getOnlinePlayers().forEach(player -> {
                int random_material = random.nextInt(materials.length);
                player.getInventory().addItem(new ItemStack(materials[random_material]));
            });
        }, 0L, 20L * s);
    }
}
