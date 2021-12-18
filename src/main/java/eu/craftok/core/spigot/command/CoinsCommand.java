package eu.craftok.core.spigot.command;

import eu.craftok.core.common.user.User;
import eu.craftok.core.spigot.CoreSpigot;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project Craftok-Core Created by Sithey
 */

public class CoinsCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                player.sendMessage("Vous avez " + CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId()).getCoins() + " coins");
            }else if (args.length == 3){
                Player cible = Bukkit.getPlayer(args[0]);
                if (cible == null)
                    return false;
                User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(cible.getUniqueId());
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
                System.out.println("args = 3");
                Player cible = Bukkit.getPlayer(args[0]);
                if (cible == null) {
                    return false;
                }
                User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(cible.getUniqueId());
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
        return false;
    }
}
