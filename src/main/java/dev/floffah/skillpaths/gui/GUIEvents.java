package dev.floffah.skillpaths.gui;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GUIEvents implements Listener {
    SkillPaths main;

    public GUIEvents(SkillPaths mn) {
        main = mn;
    }

    @EventHandler
    public void onIClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item != null && item.hasItemMeta()) {
            PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            if (pdc.has(main.keys.get("avoid"), PersistentDataType.INTEGER)) {
                e.setCancelled(true);
            }
            if (pdc.has(main.keys.get("sptype"), PersistentDataType.INTEGER)) {
                int sptype = pdc.get(main.keys.get("sptype"), PersistentDataType.INTEGER);
                if (sptype == SPTypes.SkillListBook) {
                    GUI.skillPage(new User((Player) e.getWhoClicked(), main), main);
                } else if (sptype == SPTypes.GoHomeItem) {
                    e.getWhoClicked().openInventory(GUI.mainPage(new User((Player) e.getWhoClicked(), main), main));
                }
            }
        }
    }
}
