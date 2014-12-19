package us.literat.mongodb.morphia;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 http://java.dzone.com/articles/deploying-mongodb-morphia-osgi
 */
public class DatastoreFactory {
    private MongoClient mongoClient;
    private Morphia morphia;
    private String database;

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Morphia getMorphia() {
        return morphia;
    }

    public void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Datastore get() {
        return morphia.createDatastore(mongoClient, database);
    }
}
