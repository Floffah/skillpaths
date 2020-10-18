package dev.floffah.skillpaths.util;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.util.config.ConfigProvider;
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

        val.skills.character = new ConfigCache.SkillsCache.CharacterSkillCache();

        val.skills.character.endurance = new ConfigCache.SkillsCache.CharacterSkillCache.EnduranceSKillCache();
        val.skills.character.endurance.sprintMode = conf.getInt("skills.character.endurance.sprint-mode");
        val.skills.character.endurance.timerTime = conf.getInt("skills.character.endurance.timer-time");
        val.skills.character.endurance.timerAmount = conf.getInt("skills.character.endurance.timer-amount");
        val.skills.character.endurance.secondsAmount = conf.getInt("skills.character.endurance.seconds-amount");
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
