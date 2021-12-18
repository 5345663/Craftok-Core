package eu.craftok.core.bungeecord;

import eu.craftok.core.bungeecord.command.*;
import eu.craftok.core.bungeecord.listener.ConnectionEvent;
import eu.craftok.core.bungeecord.listener.PluginMessageListener;
import eu.craftok.core.bungeecord.user.UserManager;
import eu.craftok.core.common.CoreCommon;
import eu.craftok.core.common.user.User;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Project Craftok-Core Created by Sithey
 */

public class CoreBungeecord extends Plugin {

    private static CoreBungeecord instance;
    private CoreCommon common;
    private UserManager userManager;
    @Override
    public void onEnable() {
        instance = this;
        createFile();
        this.common = new CoreCommon(CoreCommon.SERVER.BUNGEECORD, getConfig().getString("mongodb.url"), getConfig().getString("mongodb.database"),
                getConfig().getString("jedis.ip"), getConfig().getInt("jedis.port"), getConfig().getString("jedis.password"));
        this.userManager = new UserManager();
        getProxy().getPluginManager().registerListener(this, new ConnectionEvent());
        getProxy().getPluginManager().registerCommand(this, new CoinsCommand());
        getProxy().getPluginManager().registerCommand(this, new EventCommand());
        getProxy().getPluginManager().registerCommand(this, new SendMPCommand());
        getProxy().getPluginManager().registerCommand(this, new HubCommand());
        getProxy().getPluginManager().registerCommand(this, new BroadcastCommand());
        getProxy().getPluginManager().registerCommand(this, new DieSlowlyCommand());
        getProxy().getPluginManager().registerCommand(this, new ServerManagerCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffListCommand());
        getProxy().getPluginManager().registerListener(this, new PluginMessageListener());
        getProxy().registerChannel("Craftok");
    }

    public static CoreBungeecord getInstance() {
        return instance;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public CoreCommon getCommon() {
        return common;
    }

    private void createFile(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "config.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
                Configuration config = getConfig();
                if (config.get("mongodb.url") == null)
                    config.set("mongodb.url", "mongodb://plugins:E2H31A9cLsdITVfSPBj7TZuN@138.201.246.207:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false");
                if (config.get("mongodb.database") == null){
                    config.set("mongodb.database", "sithey");
                }
                if (config.get("jedis.ip") == null){
                    config.set("jedis.ip", "138.201.246.20");
                }
                if (config.get("jedis.port") == null){
                    config.set("jedis.port", 6379);
                }
                if (config.get("jedis.password") == null){
                    config.set("jedis.password", "");
                }
                saveConfig(config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPrefixOfPlayer(User u){
        ProxiedPlayer p = BungeeCord.getInstance().getPlayer(u.getUniqueId());
        return CoreCommon.getCommon().getLuckPerms().getPlayerAdapter(ProxiedPlayer.class).getMetaData(p).getPrefix();
    }

    public String getSuffixOfPlayer(User u){
        ProxiedPlayer p = BungeeCord.getInstance().getPlayer(u.getUniqueId());
        String suffix = "";
        if (CoreCommon.getCommon().getLuckPerms().getPlayerAdapter(ProxiedPlayer.class).getMetaData(p).getSuffix() != null){
            suffix = CoreCommon.getCommon().getLuckPerms().getPlayerAdapter(ProxiedPlayer.class).getMetaData(p).getSuffix();
        }

        return suffix;
    }


    public Configuration getConfig(){
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(),  "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveConfig(Configuration config){
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(),  "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startBroadcast(){
        getProxy().getScheduler().schedule(this, new Runnable() {
            @Override
            public void run() {

            }
        }, 10, 10, TimeUnit.MINUTES);
    }
}
