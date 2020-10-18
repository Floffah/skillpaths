package dev.floffah.util.user;

import dev.floffah.skillpaths.SkillPaths;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class User {
    private Player player;
    private File datafile;
    private YamlConfiguration data;

    private JavaPlugin accessor;

    public User(Player pl, JavaPlugin pluginreq) {
        if (Bukkit.getPluginManager().getPlugin("FloffahUtil") == null) {
            System.err.println("FloffahUtil plugin not installed. Disabling " + pluginreq.getName() + "...\n--- Stack trace ---");
            // VVVVV https://www.benmccann.com/printing-a-stack-trace-anywhere-in-java/ VVVVV
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            for (int i = 1; i < elements.length; i++) {
                StackTraceElement s = elements[i];
                System.out.println("\tat " + s.getClassName() + "." + s.getMethodName()
                        + "(" + s.getFileName() + ":" + s.getLineNumber() + ")");
            }
            // ^^^^^ https://www.benmccann.com/printing-a-stack-trace-anywhere-in-java/ ^^^^^
            System.err.println("--- Stack trace end ---");
            Bukkit.getPluginManager().disablePlugin(pluginreq);
        } else {
            player = pl;
            accessor = pluginreq;
            datafile = new File(Paths.get(accessor.getDataFolder().toPath().toString(), "data", pl.getUniqueId().toString() + ".yml").toString());
            data = YamlConfiguration.loadConfiguration(datafile);
            data.set("lastKnown.username", player.getName());
            data.set("lastKnown.uuid", player.getUniqueId().toString());
        }
    }

    public Player getPlayer() {
        return player;
    }

    public YamlConfiguration getData() {
        return data;
    }

    public void forceSave() throws IOException {
        data.save(datafile);
    }
}
