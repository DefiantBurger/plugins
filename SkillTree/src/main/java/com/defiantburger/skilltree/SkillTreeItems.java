package com.defiantburger.skilltree;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkillTreeItems {

    public static ItemStack getGUIFiller() {
        ItemStack item =  new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(" "));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getSkillPointDisplay(int points, int total) {
        ItemStack item =  new ItemStack(Material.EMERALD, 1);
        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.displayName(Component.text(ChatColor.BLUE + "Available Skill Points: " + points));
        List<Component> lore = new ArrayList<>();

        lore.add(Component.text(ChatColor.GRAY + "Total skill points: " + total));

        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getSkillSelector(Material material, String name, List<String> rewards, int lvl, int points) {
        ItemStack item =  new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();

        int upgrade_cost = ((lvl + 1) + (lvl % 2))/2 + 1;
        int old_upgrade_cost = (lvl + (lvl%2))/2;

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

        meta.displayName(Component.text(ChatColor.GREEN + name + " " + lvl));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(ChatColor.GRAY + "Points to upgrade: " + ChatColor.GOLD + (points-old_upgrade_cost) + "/" + upgrade_cost));
        lore.add(Component.text(""));

        lore.add(Component.text(ChatColor.GRAY + "Level " + (lvl + 1) + " Rewards:"));
        rewards.forEach(r -> lore.add(Component.text(ChatColor.BLUE + "  " + r)));
        lore.add(Component.text(""));

        lore.add(Component.text(ChatColor.YELLOW + "Click to add a skill point!"));

        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getSkillResetter() {
        ItemStack item =  new ItemStack(Material.GLASS, 1);
        ItemMeta meta = item.getItemMeta();

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_DYE, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);

        meta.displayName(Component.text(ChatColor.RED + "Reset Skill Points"));
        List<Component> lore = new ArrayList<>();

        lore.add(Component.text(ChatColor.GRAY + "Removes points from your"));
        lore.add(Component.text(ChatColor.GRAY + "skills and makes them available."));
        lore.add(Component.text(""));

        lore.add(Component.text(ChatColor.RED + "WARNING! This action is permanent!"));
        lore.add(Component.text(ChatColor.RED + "IT CANNOT BE UNDONE!"));
        lore.add(Component.text(""));

        lore.add(Component.text(ChatColor.YELLOW + "Click to reset!"));

        meta.lore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
