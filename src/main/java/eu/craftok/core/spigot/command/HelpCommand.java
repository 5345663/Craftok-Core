package eu.craftok.core.spigot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            player.sendMessage("§8§m------------------------------------------------");
            player.sendMessage("                                 §6§lAide :  ");
            player.sendMessage("                                                  ");
            player.sendMessage("    §8» §6/friends §7: Toutes les aides sur le système d'amis");
            player.sendMessage("    §8» §6/party §7: Toutes les aides sur le système de party");
            player.sendMessage("    §8» §6/msg §c[pseudo] §e[msg] §7: Envoyer un message privé");
            player.sendMessage("    §8» §6/report §c[pseudo] §7: Permet de signaler un joueur");
            player.sendMessage("    §8» §6/hub §7: Permet de retourner au lobby");
            player.sendMessage("    §8» §6/lag §7: Permet d'afficher les infos du serveur");
            player.sendMessage("                                                  ");
            player.sendMessage("§8§m------------------------------------------------");
        }
        return false;
    }
}
