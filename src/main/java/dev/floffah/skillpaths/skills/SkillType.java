package dev.floffah.skillpaths.skills;

import org.bukkit.inventory.ItemStack;

public class SkillType {
    public static final SkillType AxeThrowing = new SkillType(250);

    public int XPWorth;
    public ItemStack displayItem;

    public SkillType(int worth) {
        XPWorth = worth;
    }
}
