package dev.floffah.skillpaths.user;

import dev.floffah.skillpaths.skills.SkillType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * A User instance
 */
public class User extends dev.floffah.util.user.User {
    private String dp = "plugins.skillpaths.";

    /**
     * Instantiates a new User.
     *
     * @param pl        the player that the user references
     * @param pluginreq the plugin that requested the user
     */
    public User(Player pl, JavaPlugin pluginreq) {
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

    /**
     * Gets the skill xp of a user.
     *
     * @return the skill xp
     */
    public int getSkillXP() {
        return this.getData().getInt(dp + "skillxp");
    }

    /**
     * Add skill xp the user.
     *
     * @param skillxp the skillxp
     * @return new xp amount
     */
    public int addSkillXP(int skillxp) {
        this.getData().set(dp + "skillxp", this.getData().getInt(dp + "skillxp") + skillxp);
        return this.getData().getInt(dp + "skillxp");
    }

    /**
     * Add skill xp to the user by premade SkillType
     * @see dev.floffah.skillpaths.skills.SkillType
     *
     * @param type the SkillType
     * @return new xp amount
     */
    public int addSkillXP(SkillType type) {
        this.getData().set(dp + "skillxp", this.getData().getInt(dp + "skillxp") + type.XPWorth);
        return this.getData().getInt(dp + "skillxp");
    }
}
