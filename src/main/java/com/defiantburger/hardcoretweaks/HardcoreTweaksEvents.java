package com.defiantburger.hardcoretweaks;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class HardcoreTweaksEvents implements Listener {

    private final NamespacedKey reviver = new NamespacedKey(HardcoreTweaks.plugin, "revived");
    private final NamespacedKey taskrev = new NamespacedKey(HardcoreTweaks.plugin, "task");
    private final NamespacedKey lives = new NamespacedKey(HardcoreTweaks.plugin, "lives");

    private FileConfiguration config = HardcoreTweaks.config;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() == null)
            return;
        if (!(event.getItem().isSimilar((HardcoreTweaksItems.revivalToken))))
            return;

        event.setCancelled(true);

        if (!config.getBoolean("revival-token-enabled"))
            return;

        Random rand = new Random();
        List<Player> players = new ArrayList<>();

        Bukkit.getOnlinePlayers().forEach(p -> {
            if (p.getGameMode().equals(GameMode.SPECTATOR)) {
                players.add(p);
            }
        });

        if (players.size() == 0) {
            return;
        }

        event.getItem().setAmount(event.getItem().getAmount() - 1);

        Player player = players.get(rand.nextInt(players.size()));
        EnderCrystal ec = (EnderCrystal) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation().getBlock().getLocation().add(0.5, 0, 0.5), EntityType.ENDER_CRYSTAL);

        ec.setInvulnerable(true);

        player.teleport(ec);
        player.setGameMode(GameMode.SURVIVAL);
        player.teleport(new Location(player.getWorld(), player.getLocation().getX(), 512, player.getLocation().getZ()));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 4, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 6000, 0, true, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 99, true, false));


        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(HardcoreTweaks.plugin, new Runnable() {
            @Override
            public void run() {
                ec.setBeamTarget(player.getLocation().subtract(0, 1, 0));
            }
        }, 0L, 1L);

        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(reviver, PersistentDataType.STRING, ec.getUniqueId().toString());
        pdc.set(taskrev, PersistentDataType.INTEGER, task);

    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getEntity();
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            if (pdc.has(reviver, PersistentDataType.STRING) && pdc.has(taskrev, PersistentDataType.INTEGER)) {
                String rev = pdc.get(reviver, PersistentDataType.STRING);
                int task = pdc.get(taskrev, PersistentDataType.INTEGER);
                if (!(rev.equals(""))) {
                    pdc.set(reviver, PersistentDataType.STRING, "");
                    pdc.set(taskrev, PersistentDataType.INTEGER, -1);

                    Entity entity = Bukkit.getEntity(UUID.fromString(rev));

                    if (entity != null) {
                        entity.remove();
                    }

                    Bukkit.getScheduler().cancelTask(task);

                    player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
                    player.removePotionEffect(PotionEffectType.BLINDNESS);
                    player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);

                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        HardcoreTweaks.updateHearts(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (player.getGameMode().equals(GameMode.ADVENTURE)) {
            int myLives = player.getPersistentDataContainer().get(lives, PersistentDataType.INTEGER);
            player.getPersistentDataContainer().set(lives, PersistentDataType.INTEGER, myLives - 1);

            Player killer = player.getKiller();

            if (killer != null) {
                myLives = killer.getPersistentDataContainer().get(lives, PersistentDataType.INTEGER);
                killer.getPersistentDataContainer().set(lives, PersistentDataType.INTEGER, myLives + 1);
                HardcoreTweaks.updateHearts(killer);
            }

            HardcoreTweaks.updateHearts(player);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        if (pdc.get(lives, PersistentDataType.INTEGER) > 0) {
            player.setGameMode(GameMode.SURVIVAL);
        } else {
            player.setGameMode(GameMode.ADVENTURE);
        }

        HardcoreTweaks.updateHearts(player);
    }

}
