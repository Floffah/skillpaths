package dev.floffah.skillpaths;

import dev.floffah.skillpaths.commands.SkillPathOld;
import dev.floffah.skillpaths.commands.Skills;
import dev.floffah.skillpaths.listeners.UserStuff;
import dev.floffah.skillpaths.listeners.Util;
import dev.floffah.skillpaths.util.Messages;
import dev.floffah.util.Items.NamespaceMap;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class SkillPaths extends JavaPlugin {
    private static Economy econ;
    public HeadDatabaseAPI hdapi;
    public NamespaceMap keys;

    public File configfile;
    public YamlConfiguration config;
    public File messagesfile;
    public YamlConfiguration messagesc;
    public Messages messages;

    public static Economy getEcon() {
        return econ;
    }

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("FloffahUtil") == null) {
            getLogger().warning("Couldnt not find plugin FloffahUtil.");
            getServer().getPluginManager().disablePlugin(this);
        }
        if (getServer().getPluginManager().getPlugin("HeadDatabase") == null) {
            getLogger().warning("Couldnt not find plugin HeadDatabase.");
            getServer().getPluginManager().disablePlugin(this);
        }

        getServer().getPluginManager().registerEvents(new Util(this), this);
        vaultInit();

        getLogger().info("Enabled SkillPaths " + getDescription().getVersion());
    }

    void vaultInit() {
        Boolean did = true;
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            did = false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            did = false;
        }
        econ = rsp.getProvider();
        if (econ == null) did = false;
        if (did == false) {
            getLogger().severe("Could not connect to Vault Economy. Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        postVault();
    }

    void postVault() {
        keys = new NamespaceMap(this);

        getCommand("skillpaths").setExecutor(new Skills(this));

        if(!Files.exists(Paths.get("plugins/Skillpaths"))) {
            new File("plugins/SkillPaths").mkdirs();
        }
        if (!Files.exists(Paths.get(getDataFolder() + "/config.yml"))) {
            saveResource("config.yml", false);
        }
        if (!Files.exists(Paths.get(getDataFolder() + "/messages.yml"))) {
            saveResource("messages.yml", false);
        }
        configfile = new File(getDataFolder() + "/config.yml");
        messagesfile = new File(getDataFolder() + "/messages.yml");
        config = YamlConfiguration.loadConfiguration(configfile);
        messagesc = YamlConfiguration.loadConfiguration(messagesfile);
        messages = new Messages(messagesc);

        getServer().getPluginManager().registerEvents(new UserStuff(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled SkillPaths " + getDescription().getVersion());
    }
}
