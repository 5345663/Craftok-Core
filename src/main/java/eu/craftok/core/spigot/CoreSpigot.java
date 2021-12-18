package eu.craftok.core.spigot;

import eu.craftok.core.common.CoreCommon;
import eu.craftok.core.common.user.User;
import eu.craftok.core.spigot.command.CoinsCommand;
import eu.craftok.core.spigot.command.HelpCommand;
import eu.craftok.core.spigot.listener.PlayerConnection;
import eu.craftok.core.spigot.user.UserManager;
import eu.craftok.core.spigot.vault.EconomyService;
import eu.craftok.utils.CConfig;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Project Craftok-Core Created by Sithey
 */

public class CoreSpigot extends JavaPlugin {
    private static CoreSpigot instance;
    private CoreCommon common;
    private CConfig bdd;
    private UserManager userManager;
    @Override
    public void onEnable() {
        instance = this;
        this.bdd = new CConfig("bdd.yml", this);
        this.bdd.addValue("mongodb.url", "mongodb://plugins:E2H31A9cLsdITVfSPBj7TZuN@138.201.246.207:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass%20Community&ssl=false");
        this.bdd.addValue("mongodb.database", "sithey");
        this.bdd.addValue("jedis.ip", "138.201.246.20");
        this.bdd.addValue("jedis.port", 6379);
        this.bdd.addValue("jedis.password", "");
        this.common = new CoreCommon(CoreCommon.SERVER.SPIGOT, bdd.getConfig().getString("mongodb.url"), bdd.getConfig().getString("mongodb.database"),
                bdd.getConfig().getString("jedis.ip"), bdd.getConfig().getInt("jedis.port"), bdd.getConfig().getString("jedis.password"));
        this.userManager = new UserManager();
        Bukkit.getPluginManager().registerEvents(new PlayerConnection(), this);
        getCommand("coins").setExecutor(new CoinsCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getServer().getServicesManager().register(Economy.class, new EconomyService(), getInstance(), ServicePriority.High);
        new BukkitRunnable() {
            @Override
            public void run() {
                common.getJedis().addServer(Bukkit.getServerName());
                System.out.println("[CraftokAPI] Ajout du serveur dans le redis, prêt à recevoir des joueurs");
            }
        }.runTaskLaterAsynchronously(this, 3 * 20);
    }

    @Override
    public void onDisable() {
        common.getJedis().delServer(Bukkit.getServerName());
        System.out.println("[CraftokAPI] Delete du serveur dans le redis");
    }

    public static CoreSpigot getInstance() {
        return instance;
    }

    public String getPrefixOfPlayer(User u){
        Player p = Bukkit.getPlayer(u.getUniqueId());
        return CoreCommon.getCommon().getLuckPerms().getPlayerAdapter(Player.class).getMetaData(p).getPrefix();
    }

    public String getSuffixOfPlayer(User u){
        Player p = Bukkit.getPlayer(u.getUniqueId());
        String suffix = "";
        if (CoreCommon.getCommon().getLuckPerms().getPlayerAdapter(Player.class).getMetaData(p).getSuffix() != null){
            suffix = CoreCommon.getCommon().getLuckPerms().getPlayerAdapter(Player.class).getMetaData(p).getSuffix();
        }

        return suffix;
    }

    public CoreCommon getCommon() {
        return common;
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
