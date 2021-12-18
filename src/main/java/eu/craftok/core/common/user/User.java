package eu.craftok.core.common.user;


import eu.craftok.core.bungeecord.CoreBungeecord;
import eu.craftok.core.common.CoreCommon;
import eu.craftok.core.spigot.CoreSpigot;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Project Craftok-Core Created by Sithey
 */

public class User {

    private final String name;
    private final UUID uuid;

    public User(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public Map<String, Map<String, String>> getStatistic() {
            Map<String, Map<String, String>> newmap = new HashMap<>();

            Jedis j = null;
            try {
                j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
                Map<String, String> hmap = j.hgetAll(getUniqueId().toString());
                for (String s : hmap.keySet()){
                    if (!s.startsWith("statistics."))
                        continue;
                    String[] keys = s.split("\\.");
                    if (!newmap.containsKey(keys[1])){
                        newmap.put(keys[1], new HashMap<>());
                    }
                    newmap.get(keys[1]).put(keys[2], hmap.get(s));
                }
                return newmap;
            } finally {
                j.close();
            }
    }

    public String getStat(String key, Object defaultvalue){
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            if (!j.hexists(getUniqueId().toString(),  "statistics." + key)) {
                setStat(key, defaultvalue);
                return String.valueOf(defaultvalue);
            }
            return j.hget(getUniqueId().toString(), "statistics." + key);
        } finally {
            j.close();
        }
    }

    public void setStat(String key, Object value){
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            j.hset(getUniqueId().toString(), "statistics." + key, String.valueOf(value));
        } finally {
            j.close();
        }
    }

    public Map<String, Map<String, String>> getCosmetics() {
        Map<String, Map<String, String>> newmap = new HashMap<>();

        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            Map<String, String> hmap = j.hgetAll(getUniqueId().toString());
            for (String s : hmap.keySet()){
                if (!s.startsWith("cosmetics."))
                    continue;
                String[] keys = s.split("\\.");
                if (!newmap.containsKey(keys[1])){
                    newmap.put(keys[1], new HashMap<>());
                }
                newmap.get(keys[1]).put(keys[2], hmap.get(s));
            }
            return newmap;
        } finally {
            j.close();
        }
    }

    public String getCosmetic(String key, Object defaultvalue){
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            if (!j.hexists(getUniqueId().toString(),  "cosmetics." + key)) {
                setCosmetic(key, defaultvalue);
                return String.valueOf(defaultvalue);
            }
            return j.hget(getUniqueId().toString(), "cosmetics." + key);
        } finally {
            j.close();
        }
    }

    public void setCosmetic(String key, Object value){
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            j.hset(getUniqueId().toString(), "cosmetics." + key, String.valueOf(value));
        } finally {
            j.close();
        }
    }

    public int getCoins() {
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            return Integer.parseInt(j.hget(getUniqueId().toString(), "coins"));
        } finally {
            j.close();
        }
    }

    public void setCoins(int coins) {
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            j.hset(getUniqueId().toString(), "coins", String.valueOf(coins));
        } finally {
            j.close();
        }
    }

    public void addCoins(int coins){
        setCoins(getCoins() + coins);
    }

    public long getFirtjoin() {
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            return Long.parseLong(j.hget(getUniqueId().toString(), "firstjoin"));
        } finally {
            j.close();
        }
    }

    public void setFirtjoin(long firtjoin) {
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            j.hset(getUniqueId().toString(), "firstjoin", String.valueOf(firtjoin));
        } finally {
            j.close();
        }
    }

    public String getFriends() {
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            return j.hget(getUniqueId().toString(), "friends");
        } finally {
            j.close();
        }
    }

    public void setFriends(String friends) {
        Jedis j = null;
        try {
            j = CoreCommon.getCommon().getJedis().getUserpool().getResource();
            j.hset(getUniqueId().toString(), "friends", friends);
        } finally {
            j.close();
        }
    }

    public String getDisplayName(){
        return getDisplayGrade() + name + getSuffixGrade();
    }

    public String getDisplayGrade(){
        String prefix = "";
        if (CoreCommon.getCommon().getServer().equals(CoreCommon.SERVER.SPIGOT)){
            prefix = CoreSpigot.getInstance().getPrefixOfPlayer(this).replace('&', '§');
        }if (CoreCommon.getCommon().getServer().equals(CoreCommon.SERVER.BUNGEECORD)){
            prefix = CoreBungeecord.getInstance().getPrefixOfPlayer(this).replace('&', '§');
        }
        return prefix;
    }

    public String getSuffixGrade(){
        String suffix = "";
        if (CoreCommon.getCommon().getServer().equals(CoreCommon.SERVER.SPIGOT)){
            suffix = CoreSpigot.getInstance().getSuffixOfPlayer(this).replace('&', '§');
        }if (CoreCommon.getCommon().getServer().equals(CoreCommon.SERVER.BUNGEECORD)){
            suffix = CoreBungeecord.getInstance().getSuffixOfPlayer(this).replace('&', '§');
        }
        return suffix;
    }

    public String getGradeName(){
        return getGrade().getDisplayName();
    }

    public Group getGrade(){
        return CoreCommon.getCommon().getLuckPerms().getGroupManager().getGroup(getLuckpermuser().getPrimaryGroup());
    }

    public net.luckperms.api.model.user.User getLuckpermuser() {
        return CoreCommon.getCommon().getLuckPerms().getUserManager().getUser(uuid);
    }

}
