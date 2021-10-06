package com.defiantburger.hardcoretweaks.HardcoreTweaksCommands;

import com.defiantburger.hardcoretweaks.HardcoreTweaks;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteLives implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<>();
        FileConfiguration config = HardcoreTweaks.config;

        if (args.length == 1) {
            list.add("get");
            list.add("set");
            return list;
        } else if (args.length == 2) {
            Bukkit.getOnlinePlayers().forEach(p -> {
                list.add(p.getName());
            });
            return list;
        } else if (args.length == 3) {
            for (int i = 0; i <= config.getInt("max-lives"); i++) {
                list.add(String.valueOf(i));
            }
            return list;
        } else {
            return null;
        }
    }
}
