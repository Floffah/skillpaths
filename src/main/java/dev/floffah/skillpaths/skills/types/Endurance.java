package dev.floffah.skillpaths.skills.types;

import dev.floffah.skillpaths.skills.SkillType;
import dev.floffah.skillpaths.user.User;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Endurance extends SkillType {
    Map<UUID, SprintActionInfo> sprint = new HashMap<>();

    public Endurance() {
        super(250, "endurance", Material.DIAMOND_HELMET);
    }

    @Override
    public void postinit() {
        main.getServer().getScheduler().scheduleSyncRepeatingTask(main, () -> {
            sprint.forEach((uuid, action) -> {
                if(main.getServer().getPlayer(uuid) != null && main.getServer().getPlayer(uuid).isSprinting()) {
                    if(main.config.val.skills.character.endurance.sprintMode == 0) {
                        if(action.amount >= main.config.val.skills.character.endurance.timerTime) {
                            action.amount = 0;
                            User user = main.users.find(uuid);
                            user.addSkillXP(main.config.val.skills.character.endurance.timerAmount);
                        } else {
                            action.amount++;
                        }
                    } else if(main.config.val.skills.character.endurance.sprintMode == 1) {
                        action.amount++;
                    }
                } else {
                    if(main.config.val.skills.character.endurance.sprintMode == 1) {
                        User user = main.users.find(uuid);
                        user.addSkillXP(main.config.val.skills.character.endurance.secondsAmount * action.amount);
                        action.amount = 0;
                    }
                    sprint.remove(uuid);
                }
            });
        }, 20, 20);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getPlayer().isSprinting() && !sprint.containsKey(e.getPlayer().getUniqueId())) {
            sprint.put(e.getPlayer().getUniqueId(), new SprintActionInfo());
        } else if(e.getPlayer().isSprinting()) {
            sprint.remove(e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        sprint.remove(e.getPlayer().getUniqueId());
    }

    private static class SprintActionInfo {
        int amount = 0;
    }
}
