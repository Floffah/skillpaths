package dev.floffah.skillpaths.user;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class User extends dev.floffah.util.user.User {
    public User(Player pl, Plugin pluginreq) {
        super(pl, pluginreq);
        if(this.getData().get("plugins.skillpaths") == null) {
            this.getData().set("plugins.skillpaths.score", 0);
        }
    }
}
