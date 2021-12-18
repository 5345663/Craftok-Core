package eu.craftok.core.common.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import eu.craftok.core.common.user.User;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

/**
 * Project Craftok-Core Created by Sithey
 */

public class MongoDB {
    private String url, db;
    private MongoCollection<Document> users;
    private MongoClient client;
    private MongoDatabase database;
    public MongoDB(String url, String database){
        this.url = url;
        this.db = database;
    }

    public void initConnection(){
        client = MongoClients.create(url);
        database = client.getDatabase(db);
        users = database.getCollection("users");
    }

    public void closeConnection(){
        client.close();
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public MongoCollection<Document> getUsers() {
        return users;
    }

    public Document getDocumentByUser(User user){
        String uuid = user.getUniqueId().toString();
        return getUsers().find(eq("uuid", uuid)).first();
    }

    public void loadUser(User user){
        Document document = getDocumentByUser(user);
        if (document == null){
            document = new Document().append("uuid", user.getUniqueId().toString()).append("name", user.getName()).append("coins", 0).append("firstjoin", System.currentTimeMillis());
            getUsers().insertOne(document);
        }
        user.setCoins(document.getInteger("coins"));
        user.setFirtjoin(document.getLong("firstjoin"));

        if (document.containsKey("statistics")){
            Document k = ((Document) document.get("statistics"));
            for (String key : k.keySet()){
                Document k2 = (Document) k.get(key);
                for (String key2 : k2.keySet()){
                    user.setStat(key + "." + key2, k2.get(key2));
                }
            }
        }
        if (document.containsKey("cosmetics")){
            Document k = ((Document) document.get("cosmetics"));
            for (String key : k.keySet()){
                Document k2 = (Document) k.get(key);
                for (String key2 : k2.keySet()){
                    user.setCosmetic(key + "." + key2, k2.get(key2));
                }
            }
        }
    }

    public void saveUser(User user){
        Document document = new Document().append("uuid", user.getUniqueId().toString()).append("name", user.getName()).append("coins", user.getCoins()).append("firstjoin", user.getFirtjoin());
        Map<String, Map<String, String>> statsmap = user.getStatistic();
        if (!statsmap.isEmpty())
            document.append("statistics", statsmap);
        Map<String, Map<String, String>> cosmeticsmap = user.getCosmetics();
        if (!cosmeticsmap.isEmpty())
            document.append("cosmetics", cosmeticsmap);
        getUsers().replaceOne(eq("uuid", user.getUniqueId().toString()), document);
    }
}
