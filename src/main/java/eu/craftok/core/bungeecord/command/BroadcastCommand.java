package eu.craftok.core.bungeecord.command;

import eu.craftok.core.bungeecord.CoreBungeecord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Project Craftok-Core Created by Sithey
 */

public class BroadcastCommand extends Command {
    public BroadcastCommand() {
        super("broadcast");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)){
            StringBuilder message = new StringBuilder();
            message.append("&b&lBOUTIQUE &8&l» &7");
            for (String i : strings) {
                message.append(i).append(" ");
            }
            CoreBungeecord.getInstance().getProxy().getPlayers().forEach(cible ->
            cible.sendMessage(new TextComponent(message.toString().replace('&', '§'))));
        }
    }
}
