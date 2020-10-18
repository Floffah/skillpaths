package dev.floffah.skillpaths.util;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.util.config.ConfigProvider;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class Config extends ConfigProvider {
    public ConfigCache val;

    public Config(SkillPaths main) {
        super(main, "config.yml");
    }

    @Override
    public void load() {
        val = new ConfigCache();

        val.skills = new ConfigCache.SkillsCache();
        ConfigurationSection skills = conf.getConfigurationSection("skills");

        val.skills.character = new ConfigCache.SkillsCache.CharacterSkillCache();
        ConfigurationSection character = skills.getConfigurationSection("character");

        val.skills.character.endurance = new ConfigCache.SkillsCache.CharacterSkillCache.EnduranceSKillCache();
        ConfigurationSection endurance = character.getConfigurationSection("endurance");
        val.skills.character.endurance.sprintMode = endurance.getInt("sprint-mode");
        val.skills.character.endurance.timerTime = endurance.getInt("timer-time");
        val.skills.character.endurance.timerAmount = endurance.getInt("timer-amount");
        val.skills.character.endurance.secondsAmount = endurance.getInt("seconds-amount");
    }

    public static class ConfigCache {
        public SkillsCache skills;

        public static class SkillsCache {
            public CharacterSkillCache character;

            public static class CharacterSkillCache {
                public EnduranceSKillCache endurance;

                public static class EnduranceSKillCache {
                    public int sprintMode;
                    public int timerTime;
                    public int timerAmount;
                    public int secondsAmount;
                }
            }
        }
    }
}
