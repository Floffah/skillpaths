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
    Map<UUID, Integer> sprint = new HashMap<>();

    public Endurance() {
        super(250, "endurance", Material.DIAMOND_HELMET);
    }

    @Override
    public void postinit() {
        main.getServer().getScheduler().scheduleSyncRepeatingTask(main, () -> {
            sprint.keySet().forEach((uuid) -> {
                if (sprint.containsKey(uuid)) {
                    System.out.println("sp endur 1");
                    if (main.getServer().getPlayer(uuid) != null && main.getServer().getPlayer(uuid).isSprinting()) {
                        System.out.println("sp endur 2");
                        if (main.config.val.skills.character.endurance.sprintMode == 0) {
                            System.out.println("sp endur 3");
                            if (sprint.get(uuid) >= main.config.val.skills.character.endurance.timerTime) {
                                System.out.println("sp endur 4");
                                User user = main.users.find(uuid);
                                user.addSkillXP(main.config.val.skills.character.endurance.timerAmount);
                                sprint.replace(uuid, 0);
                            } else {
                                int amt = sprint.get(uuid);
                                sprint.replace(uuid, amt + 1);
                                System.out.println("sp endur 5 " + sprint.get(uuid) + "/" + main.config.val.skills.character.endurance.timerTime);
                            }
                        } else if (main.config.val.skills.character.endurance.sprintMode == 1) {
                            System.out.println("sp endur 6");
                            int amt = sprint.get(uuid);
                            sprint.replace(uuid, amt + 1);
                        }
                    } else {
                        System.out.println("sp endur 7");
                        if (main.config.val.skills.character.endurance.sprintMode == 1) {
                            System.out.println("sp endur 8");
                            User user = main.users.find(uuid);
                            user.addSkillXP(main.config.val.skills.character.endurance.secondsAmount * sprint.get(uuid));
                            sprint.replace(uuid, 0);
                        }
                        sprint.remove(uuid);
                    }
                }
            });
        }, 20, 20);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getPlayer().isSprinting() && !sprint.containsKey(e.getPlayer().getUniqueId())) {
            sprint.put(e.getPlayer().getUniqueId(), 0);
        } else if (!e.getPlayer().isSprinting()) {
            sprint.remove(e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        sprint.remove(e.getPlayer().getUniqueId());
    }
}
