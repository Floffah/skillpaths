package dev.floffah.skillpaths.listeners;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UserStuff implements Listener {
    SkillPaths main;

    public UserStuff(SkillPaths pl) {
        main = pl;
    }

    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        User user = new User(player, main);
    }
}
