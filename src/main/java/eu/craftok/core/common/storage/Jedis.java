package eu.craftok.core.common.storage;

import eu.craftok.core.common.user.User;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import static com.mongodb.client.model.Filters.eq;

/**
 * Project Craftok-Core Created by Sithey
 */

public class Jedis {

    private String ip, password;
    private int port;
    private JedisPool userpool, partypool, serverpool;

    public Jedis(String ip, int port, String password){
        this.ip = ip;
        this.port = port;
        this.password = password;
    }

    public void initConnection(){
        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(Jedis.class.getClassLoader());
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1024);
        jedisPoolConfig.setMaxWaitMillis(5000);
        this.userpool = new JedisPool(jedisPoolConfig, ip, port, 5000, password, 0);
        this.partypool = new JedisPool(jedisPoolConfig, ip, port, 5000, password, 1);
        this.serverpool = new JedisPool(jedisPoolConfig, ip, port, 5000, password, 2);
        Thread.currentThread().setContextClassLoader(previous);
    }

    public void stopConnection(){
        this.userpool.getResource().shutdown();
    }

    public JedisPool getUserpool() {
        return userpool;
    }

    public JedisPool getPartypool() {
        return partypool;
    }

    public void saveUser(User user){
        redis.clients.jedis.Jedis j = null;
        try {
            j = userpool.getResource();
            for (String s : j.hkeys(user.getUniqueId().toString()))
            j.hdel(user.getUniqueId().toString(), s);
        } finally {
            j.close();
        }
    }

    public void addServer(String servername){
        redis.clients.jedis.Jedis j = null;
        try {
            j = serverpool.getResource();
            j.set(servername, "ONLINE");
        } finally {
            j.close();
        }
    }

    public boolean isServerReady(String servername){
        redis.clients.jedis.Jedis j = null;
        try {
            j = serverpool.getResource();
            return j.exists(servername);
        } finally {
            j.close();
        }
    }

    public void delServer(String servername){
        redis.clients.jedis.Jedis j = null;
        try {
            j = serverpool.getResource();
            j.del(servername);
        } finally {
            j.close();
        }
    }
}
