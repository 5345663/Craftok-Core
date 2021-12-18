package eu.craftok.core.common.playerbalancer;

/**
 * Project Craftok-Core Created by Sithey
 */

public enum ServerManager {

    AUTH("Auth"),
    LOBBY("Lobby"),
    REPLAY("Replay"),
    SKYWARS("SkyWars"),
    FIRESPLEEF("Firespleef"),
    UNVUNBEDWARS("1v1Bed"),
    BEDWARS("BedWars"),
    BEDWARS2v2("BedWars2v2"),
    BEDWARS3v3("BedWars3v3"),
    BEDWARS4v4("BedWars4v4"),
    BUILDIONNARY("Build"),
    DEACOUDRE("DAC"),
    JUMPLEAGUE("JL"),
    BLOCKSUMO("BS"),
    SPEEDRUN("Speedrun"),
    VIP("VIP"),
    EVENT("Event");

    private String task;
    ServerManager(String task){
        this.task = task;
    }

    public String getTask() {
        return task;
    }

}
