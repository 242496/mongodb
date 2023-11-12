package repository.mongo;

import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import model.Machine;
import org.bson.conversions.Bson;

public class MachineMongoRepository extends AbstractMongoRepository<Machine> {

    public MachineMongoRepository() {
        super("machines", Machine.class);
    }

    public void update(Machine machine) {
        ClientSession clientSession = mongoClient.startSession();
        try {
            clientSession.startTransaction();
            MongoCollection<Machine> machinesCollection = mongoDatabase.getCollection(collectionName, Machine.class);
            Bson filter = Filters.eq("_id", machine.getEntityId());

            Bson setUpdate = Updates.combine(
                    Updates.set("cpus", machine.getCPUs()),
                    Updates.set("ram", machine.getRAM()),
                    Updates.set("disk", machine.getDisk()),
                    Updates.set("system", machine.getSystem()),
                    Updates.set("isrented", machine.getIsRented())
            );

            machinesCollection.updateOne(clientSession, filter, setUpdate);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
        } finally {
            clientSession.close();
        }
    }

    public void clearDatabase() {
        MongoCollection<Machine> collection = mongoDatabase.getCollection(collectionName, Machine.class);
        collection.drop();
    }
}
