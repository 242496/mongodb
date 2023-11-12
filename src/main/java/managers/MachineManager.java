package managers;

import java.util.List;
import model.Machine;
import repository.MachineRepository;
import repository.mongo.MachineMongoRepository;

public class MachineManager {

    private final MachineRepository machineRepository;

    public MachineManager() {
        this.machineRepository = new MachineRepository(new MachineMongoRepository());
    }

    public List<Machine> findAllMachines() {
        return machineRepository.findAll();
    }

    public Machine addMachine(Machine machine) {
        machineRepository.add(machine);
        machine.setIsRented(false);
        return machine;
    }
}
