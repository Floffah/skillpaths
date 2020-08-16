package dev.floffah.skillpaths.listeners;

import dev.floffah.skillpaths.SkillPaths;
import me.arcaniax.hdb.api.DatabaseLoadEvent;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Util implements Listener {
    SkillPaths main;
    public Util(SkillPaths mn) {
        main = mn;
    }

    @EventHandler
    public void onDatabase(DatabaseLoadEvent e) {
        main.hdapi = new HeadDatabaseAPI();
    }
}
