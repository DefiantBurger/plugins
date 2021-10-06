package com.defiantburger.hardcoretweaks.HardcoreTweaksCommands;

import com.defiantburger.hardcoretweaks.HardcoreTweaks;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CommandLives implements CommandExecutor {

    private final NamespacedKey keyLives = new NamespacedKey(HardcoreTweaks.plugin, "lives");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        int lives = pdc.get(keyLives, PersistentDataType.INTEGER);

        if (args.length < 1 || args.length > 3)
            player.sendMessage("§cIncorrect amount of arguments: the syntax is /lives <set/get> <player> [amount]");

        if (args[0].equals("set")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                try {
                    pdc.set(keyLives, PersistentDataType.INTEGER,  Integer.parseInt(args[2]));
                } catch (Exception ignored) {
                    player.sendMessage("§cInvalid lives amount.");
                }
            } else {
                player.sendMessage("§cPlayer not found.");
            }
        } else if (args[0].equals("get")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                player.sendMessage("§a" + args[1] + " Lives: §2" + lives);
            } else {
                player.sendMessage("§cPlayer not found.");
            }
        } else {
            player.sendMessage("§cIncorrect argument: the syntax is /lives <set/get> <player> [amount]");
        }

        HardcoreTweaks.updateHearts(player);

        return true;
    }
}
