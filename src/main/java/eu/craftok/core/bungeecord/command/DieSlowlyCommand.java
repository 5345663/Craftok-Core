package eu.craftok.core.bungeecord.command;

import eu.craftok.core.bungeecord.CoreBungeecord;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DieSlowlyCommand extends Command {
    public DieSlowlyCommand() {
        super("dieslowly", "dieslowly.dieslowly");
    }
    public static boolean die = false;
    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (die)
                return;
            die = true;
            BungeeCord.getInstance().stopListeners();
            if (CoreBungeecord.getInstance().getProxy().getOnlineCount() == 0) {
                CoreBungeecord.getInstance().getProxy().stop();
            }
        }
    }
}
