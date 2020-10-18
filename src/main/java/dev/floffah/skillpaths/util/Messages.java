package dev.floffah.skillpaths.util;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.util.config.ConfigProvider;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Messages extends ConfigProvider {
    public MessageCache val;

    public Messages(SkillPaths main) {
        super(main, "messages.yml");
    }

    @Override
    public void load() {
        val = new MessageCache();

        val.prefix = conf.getString("prefix");

        val.noLevels = conf.getString("no-levels");

        val.menuError = conf.getString("couldnt-open-menu");
    }

    public String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', val.prefix + message);
    }

    public static class MessageCache {
        public String prefix;

        public String noLevels;

        public String menuError;
    }
}
