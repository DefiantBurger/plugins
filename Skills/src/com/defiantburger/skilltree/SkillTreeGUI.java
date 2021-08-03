package com.defiantburger.skilltree;

import com.defiantburger.skilltree.SkillTreeItems.Items;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SkillTreeGUI {

    private Inventory inv;
    private Player player;
    private int agilityLvl;
    private int miningLvl;

    public SkillTreeGUI(Player player) {
        inv = Bukkit.createInventory(null, 27, "Skill Tree of " + player.getDisplayName());
        this.player = player;

        NBTEntity p = new NBTEntity(player);
        agilityLvl = p.getInteger("SkillTreeAgilityLevel");
        miningLvl = p.getInteger("SkillTreeMiningLevel");

        inv.setItem(10, Items.getSkillSelector(Material.LEATHER_BOOTS, "Agility", "+5% Roll Chance", agilityLvl));
        inv.setItem(11, Items.getSkillSelector(Material.DIAMOND_PICKAXE, "Mining", "+5% Double Drops Chance", miningLvl));

        for (int i = 0; i < 27; i++) {
            if (inv.getItem(i) == null)
                inv.setItem(i, Items.getGUIFiller());
        }
    }

    public Inventory getInv() {
        return inv;
    }
}
