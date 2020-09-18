package dev.floffah.skillpaths.user;

import dev.floffah.skillpaths.SkillPaths;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * The User store.
 */
public class UserStore {
    SkillPaths main;

    public HashMap<UUID, User> users;

    /**
     * Instantiates a new User store.
     *
     * @param plugin SkillPaths plugin class
     */
    public UserStore(SkillPaths plugin) {
        main = plugin;
        users = new HashMap<UUID, User>();
    }

    /**
     * Find a User by UUID
     * @see dev.floffah.skillpaths.user.User
     * @see java.util.UUID
     * @param uuid the uuid
     * @return the user
     */
    public User find(UUID uuid) {
        if(users.containsKey(uuid)) {
            return users.get(uuid);
        } else {
            User user = main.users.find(uuid);
            users.put(uuid, user);
            return user;
        }
    }

    /**
     * Find a User by UUID
     * @see dev.floffah.skillpaths.user.User
     * @see org.bukkit.entity.Player
     * @param player the player
     * @return the user
     */
    public User find(Player player) {
        if(users.containsKey(player.getUniqueId())) {
            return users.get(player.getUniqueId());
        } else {
            User user = new User(player, main);
            users.put(player.getUniqueId(), user);
            return user;
        }
    }

    /**
     * Remove a user by UUID.
     * @see java.util.UUID
     * @param uuid the uuid
     * @return whether or not it was successful.
     */
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
