package eu.craftok.core.common.playerbalancer;

import de.dytanic.cloudnet.driver.CloudNetDriver;
import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import eu.craftok.core.common.CoreCommon;
import eu.craftok.core.common.utils.TopUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Craftok-Core Created by Sithey
 */

public class PlayerBalancerManager {

    private Map<String, PlayerBalancerE> servers = new HashMap<>();

    public PlayerBalancerManager(){

    }

    public void setup(){
        for (ServerManager server : ServerManager.values()) {
            if (server == ServerManager.LOBBY || server == ServerManager.REPLAY){
                servers.put(server.getTask(), PlayerBalancerE.PROGRESSIF);
            }else{
                servers.put(server.getTask(), PlayerBalancerE.FILLER);
            }
        }
    }

    public String getServerByTask(String task){
        if (servers.get(task) == null)
            return null;
        List<TopUtils.TopUtilsObject> top = new ArrayList<>();
        PlayerBalancerE balance = servers.get(task);
        for (ServiceInfoSnapshot s : CloudNetDriver.getInstance().getCloudServiceProvider().getCloudServices(task)) {
            if (s.getProperty(BridgeServiceProperty.IS_ONLINE).isPresent() && CoreCommon.getCommon().getJedis().isServerReady(s.getServiceId().getName())) {
                Integer onlineCount = s.getProperty(BridgeServiceProperty.ONLINE_COUNT).orElse(0);
                Integer maxCount = s.getProperty(BridgeServiceProperty.MAX_PLAYERS).orElse(0);
                if (balance.equals(PlayerBalancerE.FILLER)){
                    boolean ingame = s.getProperty(BridgeServiceProperty.MOTD).orElse("PREPARING").equalsIgnoreCase("INGAME");
                    if (onlineCount + 1 >= maxCount)
                        ingame = true;
                    if (!ingame)
                    top.add(new TopUtils.TopUtilsObject(s.getServiceId().getName(), onlineCount));
                    continue;
                }
                top.add(new TopUtils.TopUtilsObject(s.getServiceId().getName(), onlineCount));
            }
        }
        top = TopUtils.getTop(top);
        if (top.isEmpty()){
            return null;
        }
        int i = 1;
        for (TopUtils.TopUtilsObject tops : top){
            i++;
        }
        if (balance.equals(PlayerBalancerE.FILLER)){
            return top.get(0).getObject().toString();
        }
        if (balance.equals(PlayerBalancerE.PROGRESSIF)){
            return top.get(top.size() - 1).getObject().toString();
        }
        return null;
    }
}
