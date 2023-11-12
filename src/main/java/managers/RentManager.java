package managers;

import java.util.List;
import java.util.UUID;
import model.Client;
import model.Machine;
import model.Rent;
import repository.ClientRepository;
import repository.MachineRepository;
import repository.RentRepository;
import repository.mongo.ClientMongoRepository;
import repository.mongo.MachineMongoRepository;
import repository.mongo.RentMongoRepository;

public class RentManager {
    private RentRepository rentRepository;
    private ClientRepository clientRepository;
    private MachineRepository machineRepository;

    public RentManager() {
        this.rentRepository = new RentRepository(new RentMongoRepository());
        this.clientRepository = new ClientRepository(new ClientMongoRepository());
        this.machineRepository = new MachineRepository(new MachineMongoRepository());
    }

    public Rent addRent(Client client, Machine machine) throws Exception {
        Rent rent;
        if(client.getActiveRents() >= client.getType().getMaxRents()) {
            throw new Exception("Client with ID: " + client.getEntityId() + " has no available rents left");
        } else if (machine.getIsRented()) {
            throw new Exception("Machine with ID: " + machine.getEntityId() + " is already rented");
        } else {
            rent = new Rent(client, machine);
            rentRepository.add(rent);
            client.setActiveRents(client.getActiveRents()+1);
            machine.setIsRented(true);
            return rent;
        }
    }

    public void removeRent(UUID ID) {
        Rent rent = rentRepository.getById(ID);
        rent.getClient().setActiveRents(rent.getClient().getActiveRents()-1);
        rent.getMachine().setIsRented(false);
        rentRepository.remove(ID);
    }

    public List<Rent> findAll() {
        List<Rent> list = rentRepository.findAll();
        return list;
    }

    public Rent getRent(UUID ID) {
        Rent rent = rentRepository.getById(ID);
        return rent;
    }

    public Rent getRentByClient(String Username){
        return rentRepository.findByClient(clientRepository.getByUsername(Username));
    }
}
