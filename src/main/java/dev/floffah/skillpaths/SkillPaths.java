package dev.floffah.skillpaths;

import dev.floffah.skillpaths.commands.SkillPath;
import dev.floffah.skillpaths.listeners.Util;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkillPaths extends JavaPlugin {
    public HeadDatabaseAPI hdapi;

    private static Economy econ;

    @Override
    public void onEnable() {
        if(getServer().getPluginManager().getPlugin("FloffahUtil") == null) {
            getLogger().warning("Couldnt not find plugin FloffahUtil.");
            getServer().getPluginManager().disablePlugin(this);
        }
        if(getServer().getPluginManager().getPlugin("HeadDatabase") == null) {
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
        if(econ == null) did = false;
        if(did == false) {
            getLogger().severe("Could not connect to Vault Economy. Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        postVault();
    }

    void postVault() {
        getCommand("skillpaths").setExecutor(new SkillPath(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled SkillPaths " + getDescription().getVersion());
    }

    public static Economy getEcon() {
        return econ;
    }
}
