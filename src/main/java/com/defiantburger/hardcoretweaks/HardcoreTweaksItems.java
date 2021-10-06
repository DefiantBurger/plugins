package com.defiantburger.hardcoretweaks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class HardcoreTweaksItems {

    public static ItemStack revivalToken;

    public static void init() { createRevivalToken(); }

    public static void createRevivalToken() {
        ItemStack item = new ItemStack(Material.END_CRYSTAL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§d§oRevival Token");
        meta.addEnchant(Enchantment.LOYALTY, 10, true);
        List<String> lore = new ArrayList<>();
        lore.add("Choose a fallen ally to resurrect");
        lore.add("They will appear at your location");
        meta.setLore(lore);
        item.setItemMeta(meta);
        revivalToken = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("revivaltoken"), item);
        sr.shape("DTD",
                "HNS",
                "DED");
        sr.setIngredient('D', Material.DIAMOND_BLOCK);
        sr.setIngredient('T', Material.TOTEM_OF_UNDYING);
        sr.setIngredient('H', Material.HEART_OF_THE_SEA);
        sr.setIngredient('N', Material.NETHER_STAR);
        sr.setIngredient('S', Material.SHULKER_BOX);
        sr.setIngredient('E', Material.END_CRYSTAL);

        Bukkit.getServer().addRecipe(sr);

    }

}
