package eu.craftok.core.bungeecord.listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import eu.craftok.core.bungeecord.CoreBungeecord;
import eu.craftok.core.common.CoreCommon;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Project Craftok-Core Created by SiriHack
 */

public class PluginMessageListener implements Listener {

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e) {
        if(e.getTag().equals("Craftok")) {
            final ByteArrayDataInput in = ByteStreams.newDataInput(e.getData());
            final String sub = in.readUTF();
            if(sub.equals("hostAnnounce")) {
                final String str1 = in.readUTF(); //Pseudo
                final String str2 = in.readUTF(); //Map // Team
                final String str3 = in.readUTF(); //Slots
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(str1);
                String servername = target.getServer().getInfo().getName();
                String srvname = "Custom";
                if(servername.contains("BedHost")){
                    srvname = "BedWars";
                }else if(servername.contains("UHCHost")){
                    srvname = "UHC";
                }else if(servername.contains("BSHost")){
                    srvname = "BlockSumo";
                }else if(servername.contains("DACHost")){
                    srvname = "Dé à coudre";
                }
                TextComponent btn = new TextComponent("§6§l[➥ JOUER]");
                btn.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/event " + servername));
                if (srvname.equalsIgnoreCase("UHC")){
                    btn.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Informations : \n §fHost : §b"+ CoreCommon.getCommon().getUserManager().getUserByName(str1).getName() + "\n §fJoueurs : §b" + str3 + "\n §fMode : §b" + str2 + "\n §fScenarios : §b\n" + in.readUTF() +"\n\n§6Clique ici pour rejoindre !").create()));
                }if (srvname.equalsIgnoreCase("coudre")){ //oué
                    btn.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Informations : \n §fHost : §b"+ CoreCommon.getCommon().getUserManager().getUserByName(str1).getName() + "\n §fJoueurs : §b" + str3 + "\n §fMap : §b" + str2 + "\n §fTeam : §b\n" + in.readUTF() +"\n\n§6Clique ici pour rejoindre !").create()));
                }else{
                    btn.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Informations : \n §fHost : §b"+ CoreCommon.getCommon().getUserManager().getUserByName(str1).getName() + "\n §fMap : §b" + str2 + "\n §fJoueurs : §b" + str3 +"\n\n§6Clique ici pour rejoindre !").create()));
                }
                String finalSrvname = srvname;
                CoreBungeecord.getInstance().getProxy().getPlayers().forEach(p -> {
                    if(p.getServer().getInfo().getName().contains("Lobby")){
                        p.sendMessage(new TextComponent(CoreCommon.getCommon().getUserManager().getUserByName(str1).getDisplayName() + "§f vient de lancer un host §c" + finalSrvname + " "), btn);
                    }
                });}
            }
        }
    }
