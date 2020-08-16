package dev.floffah.skillpaths.util;

import dev.floffah.util.chat.Colours;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
    private final YamlConfiguration c;

    public Messages(YamlConfiguration m) {
        c = m;
    }

    public String format(String message) {
        String msg;
        if (c != null) {
            if (c.getString("prefix") != null) {
                msg = c.getString("prefix")
                        + c.getString(message);
            } else {
                msg = c.getString("prefix") + " &cThere is a problem with a message in messages.yml";
            }
        } else {
            msg = "&c&lSkillPaths &8» &cThere is a problem with messages.yml";
        }
        return Colours.def(msg);
    }

    public String noLevels() {
        return format("joinMessageNoLevels");
    }

    public String menuError() {
        return format("couldntMenu");
    }
}
