package eu.craftok.core.common.user;

import eu.craftok.core.bungeecord.CoreBungeecord;
import eu.craftok.core.common.CoreCommon;
import eu.craftok.core.spigot.CoreSpigot;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project Craftok-Core Created by Sithey
 */

public class UserManager {


    private final CoreCommon common;

    public UserManager(CoreCommon common){
        this.common = common;
    }

    public User loadUser(UUID uuid){
        User user = getUserByUniqueId(uuid);
        if (common.getServer().equals(CoreCommon.SERVER.BUNGEECORD)) {
            common.getMongoDB().loadUser(user);
        }
        System.out.println("SUCCESS LOGIN " + user.getName());
        return user;

    }

    public User getUserByUniqueId(UUID uuid){
        if (common.getServer().equals(CoreCommon.SERVER.BUNGEECORD)) {
            return CoreBungeecord.getInstance().getUserManager().getUserByUniqueId(uuid);
        }else if (common.getServer().equals(CoreCommon.SERVER.SPIGOT)){
            return CoreSpigot.getInstance().getUserManager().getUserByUniqueId(uuid);
        }
        return null;
    }

    public User getUserByName(String name){
        if (common.getServer().equals(CoreCommon.SERVER.BUNGEECORD)) {
            return CoreBungeecord.getInstance().getUserManager().getUserByName(name);
        }else if (common.getServer().equals(CoreCommon.SERVER.SPIGOT)){
            return CoreSpigot.getInstance().getUserManager().getUserByName(name);
        }
        return null;
    }

    public User saveUser(UUID uuid){
        User user = getUserByUniqueId(uuid);
        if (common.getServer().equals(CoreCommon.SERVER.BUNGEECORD)) {
            common.getMongoDB().saveUser(user);
            common.getJedis().saveUser(user);
        }
        System.out.println("SUCCESS LOGOUT " + user.getName());
        return user;
    }

}
