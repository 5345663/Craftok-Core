package eu.craftok.core.bungeecord.user;

import eu.craftok.core.common.user.User;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

/**
 * Project Craftok-Core Created by Sithey
 */

public class UserManager {

    public User getUserByUniqueId(UUID uuid) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(uuid);
        if (player == null)
            return null;
        return new User(player.getName(), player.getUniqueId());
    }

    public User getUserByName(String name) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);
        if (player == null)
            return null;
        return new User(player.getName(), player.getUniqueId());
    }

}
