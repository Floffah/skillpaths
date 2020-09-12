package dev.floffah.skillpaths;

import dev.floffah.skillpaths.commands.Skills;
import dev.floffah.skillpaths.gui.GUIEvents;
import dev.floffah.skillpaths.listeners.UserStuff;
import dev.floffah.skillpaths.user.UserStore;
import dev.floffah.skillpaths.util.Glow;
import dev.floffah.skillpaths.util.Messages;
import dev.floffah.util.Items.NamespaceMap;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class SkillPaths extends JavaPlugin {
    private static Economy econ;
    public NamespaceMap keys;
    public UserStore users;

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

        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            System.err.println(e);
        }
        try {
            Glow glow = new Glow(keys.get("glow"));
            Enchantment.registerEnchantment(glow);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } catch (Exception e) {
            System.err.println(e);
        }

        //vaultInit();
        postVault();
    }

    void vaultInit() {
        boolean did = true;
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            did = false;
            getLogger().severe("Vault is not present. Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            did = false;
            getLogger().severe("Vault economy was null. Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        econ = rsp.getProvider();
        postVault();
    }

    void postVault() {
        keys = new NamespaceMap(this);
        users = new UserStore(this);

        getServer().getPluginManager().registerEvents(new GUIEvents(this), this);
        getServer().getPluginManager().registerEvents(new UserStuff(this), this);
        getCommand("skillpaths").setExecutor(new Skills(this));

        if (!Files.exists(Paths.get("plugins/Skillpaths"))) {
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


        getLogger().info("Enabled SkillPaths " + getDescription().getVersion());
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled SkillPaths " + getDescription().getVersion());
    }
}
