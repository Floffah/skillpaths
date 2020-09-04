package dev.floffah.skillpaths.user;

import dev.floffah.skillpaths.skills.SkillType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.IOException;

public class User extends dev.floffah.util.user.User {
    private String dp = "plugins.skillpaths.";

    public User(Player pl, Plugin pluginreq) {
        super(pl, pluginreq);
        if(this.getData().get("plugins.skillpaths") == null) {
            this.getData().set("plugins.skillpaths.skillxp", 0);
            try {
                forceSave();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public int getSkillXP() {
        return this.getData().getInt(dp + "skillxp");
    }

    public int addSkillXP(int skillxp) {
        this.getData().set(dp + "skillxp", this.getData().getInt(dp + "skillxp") + skillxp);
        return this.getData().getInt(dp + "skillxp");
    }

    public int addSkillXP(SkillType type) {
        this.getData().set(dp + "skillxp", this.getData().getInt(dp + "skillxp") + type.XPWorth);
        return this.getData().getInt(dp + "skillxp");
    }
}
