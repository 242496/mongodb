package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Client;
import repository.mongo.ClientMongoRepository;

public class ClientRepository implements Repository<Client> {
    private final ClientMongoRepository clientMongoRepository;

    public ClientRepository(ClientMongoRepository clientMongoRepository) {
        this.clientMongoRepository = clientMongoRepository;
    }

    @Override
    public Client add(Client entity) {
        clientMongoRepository.add(entity);
        return entity;
    }

    @Override
    public Client getById(UUID id) {
        return clientMongoRepository.findById(id);
    }


    public Client getByUsername(String Username) {
        return clientMongoRepository.findByUsername(Username);
    }

    @Override
    public void remove(UUID id) {
        clientMongoRepository.remove(id);
    }

    @Override
    public void update(Client entity) {
        clientMongoRepository.update(entity);
    }

    @Override
    public long size() {
        return findAll().size();
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        List<Client> found = clientMongoRepository.findAll();
        clients.addAll(found);
        return clients;
    }
}
