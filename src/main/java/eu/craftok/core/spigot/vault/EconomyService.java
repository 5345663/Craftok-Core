package eu.craftok.core.spigot.vault;

import eu.craftok.core.common.user.User;
import eu.craftok.core.spigot.CoreSpigot;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class EconomyService implements Economy {

    @Override
    public boolean isEnabled() {
        return CoreSpigot.getInstance().isEnabled();
    }

    @Override
    public String getName() {
        return "Craftok-Economy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return String.valueOf(amount);
    }

    @Override
    public String currencyNamePlural() {
        return "Coins";
    }

    @Override
    public String currencyNameSingular() {
        return "Coin";
    }

    @Override
    public boolean hasAccount(String playerName) {
        return true;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return true;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return true;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return true;
    }

    @Override
    public double getBalance(String playerName) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        return user.getCoins();
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        if (user == null)
            return 0;
        return user.getCoins();
    }

    @Override
    public double getBalance(String playerName, String world) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        if (user == null)
            return 0;
        return user.getCoins();
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        if (user == null)
            return 0;
        return user.getCoins();
    }

    @Override
    public boolean has(String playerName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        int money = user.getCoins();
        return money > amount;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        return user.getCoins() > amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        int money = user.getCoins();
        return money > amount;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        return user.getCoins() > amount;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        int money = user.getCoins();
        if (amount > money) {
            return new EconomyResponse(0.0D, money, EconomyResponse.ResponseType.FAILURE, "The value is more than the player's balance!");
        }
        user.addCoins((int) -amount);
        return new EconomyResponse(amount, money, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        int money = user.getCoins();
        if (amount > money) {
            return new EconomyResponse(0.0D, money, EconomyResponse.ResponseType.FAILURE, "The value is more than the player's balance!");
        }
        user.addCoins((int) -amount);
        return new EconomyResponse(amount, money, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        int money = user.getCoins();
        if (amount > money) {
            return new EconomyResponse(0.0D, money, EconomyResponse.ResponseType.FAILURE, "The value is more than the player's balance!");
        }
        user.addCoins((int) -amount);
        return new EconomyResponse(amount, money, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        int money = user.getCoins();
        if (amount > money) {
            return new EconomyResponse(0.0D, money, EconomyResponse.ResponseType.FAILURE, "The value is more than the player's balance!");
        }
        user.addCoins((int) -amount);
        return new EconomyResponse(amount, money, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        user.addCoins((int) + amount);
        return new EconomyResponse(amount, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        user.addCoins((int) + amount);
        return new EconomyResponse(amount, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByName(playerName);
        user.addCoins((int) + amount);
        return new EconomyResponse(amount, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        User user = CoreSpigot.getInstance().getCommon().getUserManager().getUserByUniqueId(player.getUniqueId());
        user.addCoins((int) + amount);
        return new EconomyResponse(amount, 0.0D, EconomyResponse.ResponseType.SUCCESS, "");
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return false;
    }
}
