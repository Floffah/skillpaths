package dev.floffah.skillpaths.gui;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.user.User;
import dev.floffah.skillpaths.util.Glow;
import dev.floffah.util.chat.Colours;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Field;
import java.util.UUID;

public class Items {
    public static ItemStack getHead(SkillPaths main, boolean avoid, HeadInfo headinfo) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headmeta = (SkullMeta) head.getItemMeta();
        headmeta.setDisplayName(Colours.def(headinfo.name));

        if (headinfo.lore != null) {
            headmeta.setLore(headinfo.lore);
        }

        PersistentDataContainer backpdc = headmeta.getPersistentDataContainer();

        if (avoid) {
            backpdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        }

        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", headinfo.texture));
        Field profilef = null;
        try {
            profilef = headmeta.getClass().getDeclaredField("profile");
            profilef.setAccessible(true);
            profilef.set(headmeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println(e);
        }

        head.setItemMeta(headmeta);
        return head;
    }

    public static ItemStack getHead(SkillPaths main, boolean avoid, int sptype, HeadInfo headinfo) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headmeta = (SkullMeta) head.getItemMeta();
        headmeta.setDisplayName(Colours.def(headinfo.name));

        if (headinfo.lore != null) {
            headmeta.setLore(headinfo.lore);
        }

        PersistentDataContainer backpdc = headmeta.getPersistentDataContainer();

        if (avoid) {
            backpdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        }
        backpdc.set(main.keys.get("sptype"), PersistentDataType.INTEGER, sptype);


        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", headinfo.texture));
        Field profilef = null;
        try {
            profilef = headmeta.getClass().getDeclaredField("profile");
            profilef.setAccessible(true);
            profilef.set(headmeta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.err.println(e);
        }

        head.setItemMeta(headmeta);
        return head;
    }

    public static ItemStack unlocks(User user, SkillPaths main) {
        ItemStack skill = new ItemStack(Material.PAPER);
        ItemMeta meta = skill.getItemMeta();

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            Glow glow = new Glow(main.keys.get("glow"));
            Enchantment.registerEnchantment(glow);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }

        Glow glow = new Glow(main.keys.get("glow"));
        meta.addEnchant(glow, 1, true);

        meta.setDisplayName(Colours.def("&9Unlock new skills"));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        skill.setItemMeta(meta);

        return skill;
    }

    public static ItemStack playerHead(User user, boolean avoid, SkillPaths main) {
        ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta pmeta = (SkullMeta) playerhead.getItemMeta();
        pmeta.setOwningPlayer(Bukkit.getOfflinePlayer(user.getPlayer().getUniqueId()));
        pmeta.setDisplayName(Colours.def("&9Skill points: &b" + user.getSkillXP()));
        if (avoid) {
            PersistentDataContainer pdc = pmeta.getPersistentDataContainer();
            pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        }
        playerhead.setItemMeta(pmeta);

        return playerhead;
    }
}
