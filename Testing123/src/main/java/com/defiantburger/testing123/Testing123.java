package com.defiantburger.testing123;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Testing123 extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);

        for (int i = 0; i < 50; i++) {
            System.out.println(ChatColor.GREEN + "[waoijdoawdm]");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[waoijdoawdm]");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
