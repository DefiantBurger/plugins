package com.defiantburger.skilltree;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTEntity;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class SkillTreeGUI {

    private Inventory inv;
    private Player player;
    private int availablePoints;
    private int agilityLvl;
    private int agilityPoints;
    private int miningLvl;
    private int miningPoints;
    private int flyingLvl;
    private int flyingPoints;

    public SkillTreeGUI(Player player) {
        inv = Bukkit.createInventory(null, 45, Component.text(ChatColor.UNDERLINE + "Skills Menu"));
        this.player = player;

        NBTEntity p = new NBTEntity(player);
        NBTCompound nbt = p.getPersistentDataContainer().getOrCreateCompound("SkillTree");
        availablePoints = nbt.getInteger("AvailablePoints");
        agilityLvl = nbt.getInteger("AgilityLevel");
        agilityPoints = nbt.getInteger("AgilityPoints");
        miningLvl = nbt.getInteger("MiningLevel");
        miningPoints = nbt.getInteger("MiningPoints");
        flyingLvl = nbt.getInteger("FlyingLevel");
        flyingPoints = nbt.getInteger("FlyingPoints");

        inv.setItem(13, SkillTreeItems.getSkillPointDisplay(availablePoints, agilityPoints + miningPoints));
        inv.setItem(19, SkillTreeItems.getSkillSelector(Material.LEATHER_BOOTS, "Agility", Arrays.asList("+5% Roll Chance","+5% Movement Speed"), agilityLvl, agilityPoints));
        inv.setItem(20, SkillTreeItems.getSkillSelector(Material.DIAMOND_PICKAXE, "Mining", Arrays.asList("+5% Double Ore Drops Chance"), miningLvl, miningPoints));
        inv.setItem(21, SkillTreeItems.getSkillSelector(Material.ELYTRA, "Flying", Arrays.asList("+5% Rocket Save Chance"), flyingLvl, flyingPoints));
        inv.setItem(31, SkillTreeItems.getSkillResetter());

        for (int i = 0; i < 45; i++) {
            if (inv.getItem(i) == null)
                inv.setItem(i, SkillTreeItems.getGUIFiller());
        }
    }

    public Inventory getInv() {
        return inv;
    }
}
