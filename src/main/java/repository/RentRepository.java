package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Client;
import model.Machine;
import model.Rent;
import repository.mongo.RentMongoRepository;

public class RentRepository implements Repository<Rent> {

    private RentMongoRepository rentMongoRepository;


    public RentRepository(RentMongoRepository rentMongoRepository) {
        this.rentMongoRepository = rentMongoRepository;
    }

    public Rent findByMachine(Machine machine) {
        return rentMongoRepository.findByMachine(machine);
    }

    public Rent findByClient(Client client) {
        return rentMongoRepository.findByClient(client);
    }

    @Override
    public Rent add(Rent entity) {
        rentMongoRepository.add(entity);
        return entity;
    }

    @Override
    public Rent getById(UUID id) {
        return rentMongoRepository.findById(id);
    }

    @Override
    public void remove(UUID id) {
        rentMongoRepository.remove(id);
    }

    @Override
    public void update(Rent entity) {
        rentMongoRepository.update(entity);
    }

    @Override
    public long size() {
        return findAll().size();
    }

    @Override
    public List<Rent> findAll() {
        List<Rent> rents = new ArrayList<>();
        List<Rent> found = rentMongoRepository.findAll();
        rents.addAll(found);
        return rents;
    }
}

