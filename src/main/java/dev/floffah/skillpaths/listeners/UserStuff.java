package dev.floffah.skillpaths.listeners;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.skills.SkillType;
import dev.floffah.skillpaths.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserStuff implements Listener {
    SkillPaths main;

    public UserStuff(SkillPaths pl) {
        main = pl;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        User user = main.users.find(player);
        if(user.getSkillXP() == 0) {
            main.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage(main.messages.noLevels());
                }
            }, 40);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        main.users.remove(e.getPlayer());
    }
}
