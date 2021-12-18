package eu.craftok.core.spigot.user;

import eu.craftok.core.common.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Project Craftok-Core Created by Sithey
 */

public class UserManager {

    public User getUserByUniqueId(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        if (player == null)
            return null;
        return new User(player.getName(), player.getUniqueId());
    }

    public User getUserByName(String name) {
        Player player = Bukkit.getPlayer(name);
        if (player == null)
            return null;
        return new User(player.getName(), player.getUniqueId());
    }

}
