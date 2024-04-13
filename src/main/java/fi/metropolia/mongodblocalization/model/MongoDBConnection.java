package fi.metropolia.mongodblocalization.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static String connectionString = "you wish";


    private MongoDBConnection() {
    }

    public static synchronized MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(connectionString);
        }
        return mongoClient;
    }
}
