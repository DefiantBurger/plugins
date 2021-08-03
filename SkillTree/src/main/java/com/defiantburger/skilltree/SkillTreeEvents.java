package com.defiantburger.skilltree;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;
import java.util.List;

public class SkillTreeEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        NBTEntity p = new NBTEntity(player);
        NBTCompound nbt = p.getPersistentDataContainer().getOrCreateCompound("SkillTree");
        if (!nbt.hasKey("GeneratedSkills")) {
            nbt.setBoolean("GeneratedSkills", Boolean.TRUE);
            nbt.setInteger("AvailablePoints", 69);

            nbt.setInteger("AgilityLevel", 12);
            nbt.setInteger("AgilityPoints", 2);

            nbt.setInteger("MiningLevel", 9);
            nbt.setInteger("MiningPoints", 4);

            nbt.setInteger("FlyingLevel", 14);
            nbt.setInteger("FlyingPoints", 8);
        }

        int agilityLvl = nbt.getInteger("AgilityLevel");
        int miningLvl = nbt.getInteger("MiningLevel");
        int flyingLvl = nbt.getInteger("FlyingLevel");

        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        attribute.setBaseValue(0.1 + (agilityLvl*0.005));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().title().equals(Component.text(ChatColor.UNDERLINE + "Skills Menu"))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent event) {
        NBTEntity p = new NBTEntity(event.getPlayer());
        NBTCompound nbt = p.getPersistentDataContainer().getOrCreateCompound("SkillTree");
        nbt.removeKey("GeneratedSkills");
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        NBTEntity p = new NBTEntity(player);
        NBTCompound nbt = p.getPersistentDataContainer().getOrCreateCompound("SkillTree");

        int agilityLvl = nbt.getInteger("AgilityLevel");

        double rand = Math.random();

        if (rand <= agilityLvl*0.05)
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent event) {
        Player player = event.getPlayer();
        NBTEntity p = new NBTEntity(player);
        NBTCompound nbt = p.getPersistentDataContainer().getOrCreateCompound("SkillTree");

        int miningLvl = nbt.getInteger("MiningLevel");

        double rand = Math.random();

        if (rand <= miningLvl*0.05) {
            List<Material> oreDrops = Arrays.asList(Material.COAL, Material.RAW_COPPER, Material.RAW_GOLD, Material.RAW_IRON, Material.DIAMOND, Material.EMERALD, Material.LAPIS_LAZULI);

            List<Item> drops = event.getItems();

            drops.forEach(d -> {
                if (oreDrops.contains(d.getItemStack().getType()))
                    d.getItemStack().setAmount(d.getItemStack().getAmount() * 2);
            });
        }
    }

    @EventHandler
    public void onFireworkUse(PlayerElytraBoostEvent event) {
        Player player = event.getPlayer();
        NBTEntity p = new NBTEntity(player);
        NBTCompound nbt = p.getPersistentDataContainer().getOrCreateCompound("SkillTree");

        int flyingLvl = nbt.getInteger("FlyingLevel");

        double rand = Math.random();

        if (rand <= flyingLvl*0.05) {
            event.getItemStack().setAmount(event.getItemStack().getAmount() + 1);
        }
    }

}
