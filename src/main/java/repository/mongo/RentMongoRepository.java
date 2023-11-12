package repository.mongo;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import model.Client;
import model.Machine;
import model.Rent;
import org.bson.conversions.Bson;

public class RentMongoRepository extends AbstractMongoRepository<Rent> {


    public RentMongoRepository() {
        super("rents", Rent.class);
    }
    public Rent findByClient(Client client) {
        MongoCollection<Rent> collection = mongoDatabase.getCollection(collectionName, Rent.class);
        Bson filter = Filters.eq("client._id", client.getEntityId());
        return collection.find().filter(filter).first();
    }
    public Rent findByMachine(Machine machine) {
        MongoCollection<Rent> collection = mongoDatabase.getCollection(collectionName, Rent.class);
        Bson filter = Filters.eq("machine._id", machine.getEntityId());
        return collection.find().filter(filter).first();
    }
    public void update(Rent rent) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction();
            MongoCollection<Rent> clientsCollection = mongoDatabase.getCollection(collectionName, Rent.class);
            Bson filter = Filters.eq("_id", rent.getEntityId());
            Bson setUpdate = Updates.combine(
                    Updates.set("client", rent.getClient()),
                    Updates.set("machine", rent.getMachine())
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
        MongoCollection<Rent> collection = mongoDatabase.getCollection(collectionName, Rent.class);
        collection.drop();
    }
}
