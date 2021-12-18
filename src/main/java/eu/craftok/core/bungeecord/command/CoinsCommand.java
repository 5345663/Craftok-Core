package eu.craftok.core.bungeecord.command;

import eu.craftok.core.bungeecord.CoreBungeecord;
import eu.craftok.core.common.user.User;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Project Craftok-Core Created by Sithey
 */

public class CoinsCommand extends Command {
    public CoinsCommand() {
        super("coins");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if (args.length == 0){
                player.sendMessage(new TextComponent("Vous avez " + CoreBungeecord.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId()).getCoins() + " coins"));
            }else if (args.length == 3){
                ProxiedPlayer cible = CoreBungeecord.getInstance().getProxy().getPlayer(args[0]);
                if (cible == null)
                    return;
                User user = CoreBungeecord.getInstance().getCommon().getUserManager().getUserByUniqueId(cible.getUniqueId());
                if (player.hasPermission("core.admin.coins")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        user.setCoins(Integer.parseInt(args[2]));
                    }
                    if (args[1].equalsIgnoreCase("add")) {
                        user.addCoins(Integer.parseInt(args[2]));
                    }
                    if (args[1].equalsIgnoreCase("remove")) {
                        user.addCoins(Integer.parseInt(args[2]) * -1);
                    }
                }
            }
        }else{
            if (args.length == 3){
                ProxiedPlayer cible = CoreBungeecord.getInstance().getProxy().getPlayer(args[0]);
                if (cible == null)
                    return;
                User user = CoreBungeecord.getInstance().getCommon().getUserManager().getUserByUniqueId(cible.getUniqueId());
                if (args[1].equalsIgnoreCase("set")){
                    user.setCoins(Integer.parseInt(args[2]));
                }
                if (args[1].equalsIgnoreCase("add")){
                    user.addCoins(Integer.parseInt(args[2]));
                }
                if (args[1].equalsIgnoreCase("remove")){
                    user.addCoins(Integer.parseInt(args[2]) * -1);
                }
            }
        }
    }
}
