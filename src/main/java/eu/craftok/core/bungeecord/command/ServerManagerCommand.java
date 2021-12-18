package eu.craftok.core.bungeecord.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.net.InetSocketAddress;

/**
 * Project Craftok-Core Created by Sithey
 */

public class ServerManagerCommand extends Command {
    public ServerManagerCommand() {
        super("servermanager", "craftok.admin", "svm");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
//        /smv addserver Nom ip:port
//        /smv remserver Nom
        if (commandSender instanceof ProxiedPlayer){
            if (strings[0].equalsIgnoreCase("addserver")) {
                if (strings.length != 3) {
                    return;
                }
                String nom = strings[1];
                String ip = strings[2].split(":")[0];
                int port = Integer.parseInt(strings[2].split(":")[1]);
                ProxyServer.getInstance().getServers().put(nom, ProxyServer.getInstance().constructServerInfo(nom, new InetSocketAddress(ip, port), "WAITING", false));
            }
            if (strings[0].equalsIgnoreCase("remserver")) {
                if (strings.length != 2) {
                    return;
                }
                String nom = strings[1];
                ProxyServer.getInstance().getServers().remove(nom);
            }
        }
    }
}
