package dev.floffah.skillpaths.gui;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.skills.SkillType;
import dev.floffah.skillpaths.user.User;
import dev.floffah.skillpaths.util.Glow;
import dev.floffah.util.chat.Colours;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Field;
import java.util.Arrays;

public class GUI implements Listener {
    public static void openInv(Player player, SkillPaths main) {
        User user = new User(player, main);

        user.getPlayer().openInventory(mainPage(user, main));
    }

    public static Inventory mainPage(User user, SkillPaths main) {
        Inventory inv = Bukkit.createInventory(null, 54);

        ItemStack infobook = new ItemStack(Material.BOOK, 1);
        ItemMeta bookmeta = infobook.getItemMeta();
        bookmeta.setDisplayName(Colours.def("&b&lAll skills"));
        PersistentDataContainer pdc = bookmeta.getPersistentDataContainer();
        pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        pdc.set(main.keys.get("sptype"), PersistentDataType.INTEGER, SPTypes.SkillListBook);
        infobook.setItemMeta(bookmeta);

        inv.setItem(11, playerHead(user, true, main));
        inv.setItem(13, infobook);
        inv.setItem(15, unlocks(user, main));

        addBorder(inv, main);

        return inv;
    }

    public static void skillPage(User user, SkillPaths main) {
        Inventory inv = Bukkit.createInventory(null, 54);

        ItemStack back = main.hdapi.getItemHead("10414");
        if (back != null) {
            ItemMeta backmeta = back.getItemMeta();
            backmeta.setDisplayName(Colours.def("&b&lBack"));
            PersistentDataContainer backpdc = backmeta.getPersistentDataContainer();
            backpdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
            backpdc.set(main.keys.get("sptype"), PersistentDataType.INTEGER, SPTypes.GoHomeItem);
            back.setItemMeta(backmeta);
            System.out.println("yes");
            inv.setItem(10, back);
        } else {
            user.getPlayer().closeInventory();
            user.getPlayer().sendMessage(main.messages.menuError());
            return;
        }

        addBorder(inv, main);

        user.getPlayer().openInventory(inv);
    }

    public static ItemStack getSkillItem(SkillType type) {
        return type.displayItem;
    }

    public static ItemStack unlocks(User user, SkillPaths main) {
        ItemStack skill = new ItemStack(Material.PAPER);
        ItemMeta meta = skill.getItemMeta();

        try{
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

    public static void addBorder(Inventory inv, SkillPaths main) {
        inv.setItem(0, borderItem(main));
        inv.setItem(1, borderItem(main));
        inv.setItem(2, borderItem(main));
        inv.setItem(3, borderItem(main));
        inv.setItem(4, borderItem(main));
        inv.setItem(5, borderItem(main));
        inv.setItem(6, borderItem(main));
        inv.setItem(7, borderItem(main));
        inv.setItem(8, borderItem(main));
        inv.setItem(9, borderItem(main));
        inv.setItem(17, borderItem(main));
        inv.setItem(18, borderItem(main));
        inv.setItem(26, borderItem(main));
        inv.setItem(27, borderItem(main));
        inv.setItem(35, borderItem(main));
        inv.setItem(36, borderItem(main));
        inv.setItem(44, borderItem(main));
        inv.setItem(45, borderItem(main));
        inv.setItem(46, borderItem(main));
        inv.setItem(47, borderItem(main));
        inv.setItem(48, borderItem(main));
        inv.setItem(49, borderItem(main));
        inv.setItem(50, borderItem(main));
        inv.setItem(51, borderItem(main));
        inv.setItem(52, borderItem(main));
        inv.setItem(53, borderItem(main));
    }

    public static ItemStack borderItem(SkillPaths main) {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colours.def("&0Nothing here"));
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        item.setItemMeta(meta);
        return item;
    }
}

