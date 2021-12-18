package eu.craftok.core.bungeecord.command;

import eu.craftok.core.bungeecord.CoreBungeecord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Project Craftok-Core Created by Sithey
 */

public class SendMPCommand extends Command {
    public SendMPCommand() {
        super("sendmp");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)){
            if (strings.length <= 1){
                return;
            }
            ProxiedPlayer cible = CoreBungeecord.getInstance().getProxy().getPlayer(strings[0]);
            StringBuilder message = new StringBuilder();
            for (int i = 1; i <= strings.length -1; i++) {
                message.append(strings[i]).append(" ");
            }
            cible.sendMessage(new TextComponent(message.toString().replace('&', '§')));
        }
    }
}
