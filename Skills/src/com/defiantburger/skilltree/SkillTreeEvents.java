package com.defiantburger.skilltree;

import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SkillTreeEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        NBTEntity p = new NBTEntity(event.getPlayer());
        if (!p.hasKey("GeneratedSkills")) {
            p.setBoolean("GeneratedSkills", Boolean.TRUE);
            p.setInteger("SkillTreeAvailablePoints", 0);

            p.setInteger("SkillTreeAgilityLevel", 0);
            p.setInteger("SkillTreeAgilityPoints", 0);

            p.setInteger("SkillTreeMiningLevel", 0);
            p.setInteger("SkillTreeMiningPoints", 0);

            p.setInteger("SkillTreeFlyingLevel", 0);
            p.setInteger("SkillTreeFlyingPoints", 0);
        }
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        event.getPlayer().openInventory(new SkillTreeGUI(event.getPlayer()).getInv());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();


    }

}
