package dev.floffah.skillpaths.user;

import dev.floffah.skillpaths.SkillPaths;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class UserStore {
    SkillPaths main;

    public HashMap<UUID, User> users;

    public UserStore(SkillPaths plugin) {
        main = plugin;
        users = new HashMap<UUID, User>();
    }

    public User find(UUID uuid) {
        if(users.containsKey(uuid)) {
            return users.get(uuid);
        } else {
            User user = main.users.find(uuid);
            users.put(uuid, user);
            return user;
        }
    }

    public User find(Player player) {
        if(users.containsKey(player.getUniqueId())) {
            return users.get(player.getUniqueId());
        } else {
            User user = new User(player, main);
            users.put(player.getUniqueId(), user);
            return user;
        }
    }

    public boolean remove(UUID uuid) {
        if(users.containsKey(uuid)) {
            User user = users.get(uuid);
            try {
                user.forceSave();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public void remove(Player player) {
        remove(player.getUniqueId());
    }
}
