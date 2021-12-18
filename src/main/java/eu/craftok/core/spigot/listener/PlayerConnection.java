package eu.craftok.core.spigot.listener;

import eu.craftok.core.bungeecord.CoreBungeecord;
import eu.craftok.core.spigot.CoreSpigot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Project Craftok-Core Created by Sithey
 */

public class PlayerConnection implements Listener {

    @EventHandler (priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event){
        CoreSpigot.getInstance().getCommon().getUserManager().loadUser(event.getPlayer().getUniqueId());
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event){
        CoreSpigot.getInstance().getCommon().getUserManager().saveUser(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event){
        if (event.getResult().equals(PlayerLoginEvent.Result.KICK_FULL))
            if (event.getPlayer().hasPermission("craftok.staff"))
                event.setResult(PlayerLoginEvent.Result.ALLOWED);
    }

}
