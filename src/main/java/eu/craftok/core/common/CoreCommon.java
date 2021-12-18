package eu.craftok.core.common;

import eu.craftok.core.common.playerbalancer.PlayerBalancerManager;
import eu.craftok.core.common.storage.Jedis;
import eu.craftok.core.common.storage.MongoDB;
import eu.craftok.core.common.user.UserManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

/**
 * Project Craftok-Core Created by Sithey
 */

public class CoreCommon {

    private static CoreCommon common;
    public enum SERVER {BUNGEECORD, SPIGOT, CLOUDNET};

    private final SERVER server;
    private final MongoDB mongoDB;
    private final Jedis jedis;
    private final LuckPerms luckPerms;
    private final UserManager userManager;
    private final PlayerBalancerManager playerBalancerManager;

    public CoreCommon(SERVER server, String urlmongo, String database, String ipredis, int portredis, String passwordredis){
        this.server = server;
        (this.mongoDB = new MongoDB(urlmongo, database)).initConnection();
        (this.jedis = new Jedis(ipredis, portredis, passwordredis)).initConnection();
        this.luckPerms = LuckPermsProvider.get();
        this.userManager = new UserManager(this);
        (this.playerBalancerManager = new PlayerBalancerManager()).setup();
        common = this;
    }

    public SERVER getServer() {
        return server;
    }

    public MongoDB getMongoDB() {
        return mongoDB;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public PlayerBalancerManager getPlayerBalancerManager() {
        return playerBalancerManager;
    }

    public static CoreCommon getCommon() {
        return common;
    }
}
