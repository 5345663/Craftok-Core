package eu.craftok.core.bungeecord.command;

import de.dytanic.cloudnet.CloudNet;
import eu.craftok.core.bungeecord.CoreBungeecord;
import eu.craftok.core.common.user.User;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class EventCommand extends Command {
    public EventCommand() {
        super("event");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            User user = CoreBungeecord.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
            if (strings.length == 1){
                if (strings[0].contains("UHCHost") || strings[0].contains("BedHost")  || strings[0].contains("BSHost") || strings[0].contains("DACHost")){
                    player.connect(CoreBungeecord.getInstance().getProxy().getServerInfo(strings[0]));
                }
            }
        }
    }
}
