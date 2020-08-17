package dev.floffah.skillpaths.skills;

import dev.floffah.skillpaths.user.User;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SkillType {
    public static final SkillType AxeThrowing = new SkillType(250, "axethrowing", Material.DIAMOND_AXE);

    public int XPWorth;
    public ItemStack displayItem;

    public String name;

    public SkillType(int worth, String skillname, Material material) {
        XPWorth = worth;
        name = skillname;
    }

    public boolean isUnlocked(User user) {
        return user.getData().getBoolean("plugins.skillpaths.skills." + name + ".unlocked");
    }
}
