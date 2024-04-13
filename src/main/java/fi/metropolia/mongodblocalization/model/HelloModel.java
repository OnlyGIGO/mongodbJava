package fi.metropolia.mongodblocalization.model;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class HelloModel {
     private MongoDatabase database=null;
    private MongoClient mongoClient = MongoDBConnection.getMongoClient();
    public HelloModel() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register(User.class).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        try  {
            database = mongoClient.getDatabase("javatest").withCodecRegistry(pojoCodecRegistry);;
            System.out.println("Connected to the database:"+database.getName());
        } catch (MongoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    public boolean createUser(String id,String name,int age, String city) {
        try{
        MongoCollection<User> collection = database.getCollection("users", User.class);
        User user = new User();
        user.setName(name);
        user.setCity(city);
        user.setAge(age);
        user.setId(id);
        collection.insertOne(user);
        return true;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public User readUser(String id) {
        try{
        MongoCollection<User> collection = database.getCollection("users", User.class);
        return collection.find(eq("_id", id)).first();}catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public boolean updateUser(String id, String name, int age, String city) {
        try {
            MongoCollection<User> collection = database.getCollection("users", User.class);
            User user = new User();
            user.setName(name);
            user.setCity(city);
            user.setAge(age);
            collection.replaceOne(eq("_id", id), user);
            return true;
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(String id) {
        try{
        MongoCollection<User> collection = database.getCollection("users", User.class);
        collection.deleteOne(eq("_id", id));
        return true;}catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        if (database != null) {
            database = null;
        }
    }




}
