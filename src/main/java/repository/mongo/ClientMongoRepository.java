package repository.mongo;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import model.Client;
import org.bson.conversions.Bson;

public class ClientMongoRepository extends AbstractMongoRepository<Client> {


    public ClientMongoRepository() {
        super("clients", Client.class);
    }

    public Client findByUsername(String Username) {
        MongoCollection<Client> collection = mongoDatabase.getCollection(collectionName, Client.class);
        Bson filter = Filters.eq("username", Username);
        return collection.find().filter(filter).first();
    }

    public void update(Client client) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction();
            MongoCollection<Client> clientsCollection = mongoDatabase.getCollection(collectionName, Client.class);
            Bson filter = Filters.eq("_id", client.getEntityId());

            Bson setUpdate = Updates.combine(
                    Updates.set("username", client.getUsername()),
                    Updates.set("type", client.getType()),
                    Updates.set("activerents", client.getActiveRents())
            );

            clientsCollection.updateOne(clientSession, filter, setUpdate);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }
    }

    public void clearDatabase() {
        MongoCollection<Client> collection = mongoDatabase.getCollection(collectionName, Client.class);
        collection.drop();
    }
}
