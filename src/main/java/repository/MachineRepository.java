package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Machine;
import repository.mongo.MachineMongoRepository;

public class MachineRepository implements Repository<Machine> {
    private final MachineMongoRepository machineMongoRepository;

    public MachineRepository(MachineMongoRepository machineMongoRepository) {
        this.machineMongoRepository = machineMongoRepository;
    }

    @Override
    public Machine add(Machine entity) {
        machineMongoRepository.add(entity);
        return entity;
    }

    @Override
    public Machine getById(UUID id) {
        return machineMongoRepository.findById(id);
    }

    @Override
    public void remove(UUID id) {
        machineMongoRepository.remove(id);
    }

    @Override
    public void update(Machine entity) {
        machineMongoRepository.update(entity);
    }

    @Override
    public long size() {
        return findAll().size();
    }

    @Override
    public List<Machine> findAll() {
        List<Machine> machines = new ArrayList<>();
        List<Machine> found = machineMongoRepository.findAll();
        machines.addAll(found);
        return machines;
    }
}
