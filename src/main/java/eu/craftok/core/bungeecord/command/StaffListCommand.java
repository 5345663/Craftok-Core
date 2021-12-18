package eu.craftok.core.bungeecord.command;

import eu.craftok.core.common.user.User;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Craftok-Core Created by Sithey
 */

public class StaffListCommand extends Command {
    public StaffListCommand() {
        super("stafflist", "craftok.staff", "sl");
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage(new TextComponent(""));
        List<String> perms = new ArrayList<>(Arrays.asList("noalerts", "admin", "respdev" ,"dev", "respmod", "modo", "builder"));
        commandSender.sendMessage(new TextComponent("§7§m---|--------------§r§7>§r§f§l STAFF LIST §r§7<§m-------------|---"));
        perms.forEach(p -> {
            StringBuilder sb = new StringBuilder();
            List<User> players = new ArrayList<>();
//            CoreCommon.getCommon().getUserManager().getUsers().forEach(player -> {
//                if (player.getGradeName().equalsIgnoreCase(p)){
//                    players.add(player);
//                }
//            });
            if (!players.isEmpty()){
                players.forEach(player -> sb.append(player.getName()).append(" "));
                commandSender.sendMessage(new TextComponent(players.get(0).getDisplayGrade() + " (" + players.size() + ")" + ": " + sb));
            }
        });
        commandSender.sendMessage(new TextComponent(""));
    }
}

