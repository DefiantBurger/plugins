package com.defiantburger.skilltree.SkillTreeItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static ItemStack getGUIFiller() {
        ItemStack item =  new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSkillSelector(Material material, String name, String reward, int lvl) {
        ItemStack item =  new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        int upgrade_cost = ((lvl + 1) + (lvl % 2))/2 + 1;

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

        meta.setDisplayName(ChatColor.GREEN + name + " " + lvl);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Cost to upgrade: " + ChatColor.GOLD + upgrade_cost);
        lore.add("");
        lore.add(ChatColor.GRAY + "Level " + (lvl + 1) + " Rewards:");
        lore.add(ChatColor.BLUE + "  " + reward);

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
