package eu.craftok.core.bungeecord.command;

import eu.craftok.core.bungeecord.CoreBungeecord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Project Craftok-Core Created by Sithey
 */

public class HubCommand extends Command {
    public HubCommand() {
        super("hub", "","lobby");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            player.connect(CoreBungeecord.getInstance().getProxy().getServerInfo("Lobby"));
        }
    }
}
