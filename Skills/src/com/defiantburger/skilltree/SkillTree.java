package com.defiantburger.skilltree;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class SkillTree extends JavaPlugin {

    public static HashMap<Player, SkillTreeGUI> skillGUIs = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SkillTreeEvents(), this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[SkillTree] Plugin enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[SkillTree] Plugin disabled!");
    }

}
