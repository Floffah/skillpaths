package dev.floffah.skillpaths.gui;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.skills.SkillType;
import dev.floffah.skillpaths.user.User;
import dev.floffah.util.chat.Colours;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GUI implements Listener {
    public static void openInv(Player player, SkillPaths main) {
        User user = main.users.find(player);

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

        SkillType[] skills = new SkillType[] {SkillType.Endurance, SkillType.Agility};
        int[] skillput = new int[] {20,21,22,23,24,25,26,29,30,31,32,33,34,35,38,39,41,42,43,44};

        for(int i = 0;i<skills.length;i++) {
            inv.setItem(skillput[i] - 1, Items.getSkillPane(user, main, skills[i]));
        }

        addBorder(inv, main);

        user.getPlayer().openInventory(inv);
    }

    public static ItemStack getSkillItem(SkillType type) {
        return type.displayItem;
    }

    public static void addBorder(Inventory inv, SkillPaths main) {
        int[] put = new int[] {0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,49,50,51,52,53};

        for(int i = 0;i<put.length;i++) {
            inv.setItem(put[i], borderItem(main));
        }
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

