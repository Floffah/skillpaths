package dev.floffah.skillpaths.gui;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.user.User;
import dev.floffah.util.chat.Colours;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class GUI implements Listener {
    SkillPaths main;
    public GUI(SkillPaths pl) {
        main = pl;
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public void openInv(Player player) {
        User user = new User(player, main);

        user.getPlayer().openInventory(mainPage(user));
    }

    public Inventory mainPage(User user) {
        Inventory inv = Bukkit.createInventory(null, 54);

        ItemStack infobook = new ItemStack(Material.BOOK, 1);
        ItemMeta bookmeta = infobook.getItemMeta();
        bookmeta.setDisplayName(Colours.def("&b&lAll skills"));
        PersistentDataContainer pdc = bookmeta.getPersistentDataContainer();
        pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        infobook.setItemMeta(bookmeta);

        inv.setItem(11, playerHead(user, true));
        inv.setItem(13, infobook);
        inv.setItem(15, playerskill(user));

        return inv;
    }

    public ItemStack playerskill(User user) {
        ItemStack skill = new ItemStack(Material.WHITE_WOOL);
        ItemMeta meta = skill.getItemMeta();

        if(user.getSkillXP() < 100) {
            skill.setType(Material.RED_WOOL);
            meta.setDisplayName(Colours.def("&bSkill: &c&lComplete Beginner"));
        }

        meta.setLore(Arrays.asList(Colours.def("&9Your Skill XP: &3" + user.getSkillXP())));
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        skill.setItemMeta(meta);

        return skill;
    }

    public ItemStack playerHead(User user, boolean avoid) {
        ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta pmeta = (SkullMeta) playerhead.getItemMeta();
        pmeta.setOwningPlayer(Bukkit.getOfflinePlayer(user.getPlayer().getUniqueId()));
        pmeta.setDisplayName(Colours.def("&b" + user.getPlayer().getName() + "'s Skills"));
        if(avoid) {
            PersistentDataContainer pdc = pmeta.getPersistentDataContainer();
            pdc.set(main.keys.get("avoid"), PersistentDataType.INTEGER, 1);
        }
        playerhead.setItemMeta(pmeta);

        return playerhead;
    }
}
