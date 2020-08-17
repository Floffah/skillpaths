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

        inv.setItem(11, Items.playerHead(user, true, main));
        inv.setItem(13, infobook);
        inv.setItem(15, Items.unlocks(user, main));

        addBorder(inv, main);

        return inv;
    }

    public static void skillPage(User user, SkillPaths main) {
        Inventory inv = Bukkit.createInventory(null, 54);

        ItemStack back = Items.getHead(main, true, SPTypes.GoHomeItem, HeadInfo.cyanBack);

        inv.setItem(10, back);
        inv.setItem(28, Items.getSkillPane(user, main, SkillType.AxeThrowing));

        addBorder(inv, main);

        user.getPlayer().openInventory(inv);
    }

    public static ItemStack getSkillItem(SkillType type) {
        return type.displayItem;
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
        meta.setDisplayName(Colours.def("&0&lNothing here"));
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        item.setItemMeta(meta);
        return item;
    }
}

