package com.defiantburger.hardcoretweaks;

import com.defiantburger.hardcoretweaks.HardcoreTweaksCommands.CommandLives;
import com.defiantburger.hardcoretweaks.HardcoreTweaksCommands.TabCompleteLives;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class HardcoreTweaks extends JavaPlugin {

    // §

    public static Plugin plugin;
    public static FileConfiguration config;

    private static NamespacedKey lives;

    public void setKeys() {
        lives = new NamespacedKey(this, "lives");
    }

    @Override
    public void onEnable() {
        plugin = this;
        loadConfig();
        setKeys();
        HardcoreTweaksItems.init();
        getServer().getPluginManager().registerEvents(new HardcoreTweaksEvents(), this);
        getCommand("lives").setExecutor(new CommandLives());
        getCommand("lives").setTabCompleter(new TabCompleteLives());

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[HardcoreTweaks] Plugin started!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[HardcoreTweaks] Plugin stopped!");
    }

    public static void updateHearts(Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        int myLives = pdc.get(lives, PersistentDataType.INTEGER);

        if (!(pdc.has(lives, PersistentDataType.INTEGER)))
            pdc.set(lives, PersistentDataType.INTEGER, config.getInt("starting-lives"));

        int maxLives = config.getInt("max-lives");
        if (myLives > maxLives) {
            myLives = maxLives;
            pdc.set(lives, PersistentDataType.INTEGER, maxLives);
        }

        if (myLives == 0) {
            player.setGameMode(GameMode.ADVENTURE);
        }

        StringBuilder hearts = new StringBuilder();

        if (myLives > 0) {
            hearts.append(" §c");
            for (int i = 0; i < myLives; i++) {
                hearts.append("❤");
            }
        }

        player.setPlayerListName(player.getDisplayName() + hearts);
    }

    private void loadConfig() {
        config = this.getConfig();
        config.addDefault("starting-lives", 3);
        config.addDefault("max-lives", 5);
        config.addDefault("revival-token-enabled", true);

        config.options().copyDefaults(true);
        saveConfig();
    }

}
