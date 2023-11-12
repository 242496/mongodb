package managers;

import java.util.List;
import model.Client;
import repository.ClientRepository;
import repository.mongo.ClientMongoRepository;

public class ClientManager {
    private final ClientRepository clientRepository;

    public ClientManager() {
        this.clientRepository = new ClientRepository(new ClientMongoRepository());
    }

    public Client getClient(String Username) {
        return clientRepository.getByUsername(Username);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        clientRepository.add(client);
        client.setActiveRents(0);
        return client;
    }
}
