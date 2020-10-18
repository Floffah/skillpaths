package dev.floffah.skillpaths.skills;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.user.User;
import dev.floffah.util.chat.Colours;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SkillType implements Listener {
    SkillPaths main;

    public int XPWorth;
    public ItemStack displayItem;

    public String name;

    public void init(SkillPaths main) {
        this.main = main;

        registerListeners();
    }

    public SkillType(int worth, String skillname, Material material) {
        XPWorth = worth;
        name = skillname;

        ItemStack display = new ItemStack(material);
        ItemMeta dmeta = display.getItemMeta();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        dmeta.setDisplayName(Colours.def("&b&l" + name));

    }

    public void registerListeners() {
        
    }

    public boolean isUnlocked(User user) {
        return user.getData().getBoolean("plugins.skillpaths.skills." + name + ".unlocked");
    }
}
