package eu.craftok.core.bungeecord.listener;

import eu.craftok.core.bungeecord.CoreBungeecord;
import eu.craftok.core.bungeecord.command.DieSlowlyCommand;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Project Craftok-Core Created by Sithey
 */

public class ConnectionEvent implements Listener {

    @EventHandler
    public void onJoin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        CoreBungeecord.getInstance().getCommon().getUserManager().loadUser(player.getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerDisconnectEvent event) {
        CoreBungeecord.getInstance().getCommon().getUserManager().saveUser(event.getPlayer().getUniqueId());
        if (DieSlowlyCommand.die) {
            if (CoreBungeecord.getInstance().getProxy().getOnlineCount() <= 1) {
                CoreBungeecord.getInstance().getProxy().stop();
            }
        }
    }

    @EventHandler
    public void onSwitchServer(ServerConnectEvent event){
        ProxiedPlayer player = event.getPlayer();
        ServerInfo target = event.getTarget();
        String newtarget = CoreBungeecord.getInstance().getCommon().getPlayerBalancerManager().getServerByTask(target.getName());
        if (newtarget != null) {
            event.setTarget(CoreBungeecord.getInstance().getProxy().getServerInfo(newtarget));
        }
    }



}
