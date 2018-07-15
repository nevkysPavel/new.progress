package all.service;

import all.dao.ClientDao;
import all.entity.Client;
import all.entity.FoodAndActivity;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Service

public class ClientServiceImpl implements ClientService {

    final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao){
        this.clientDao = clientDao;
    }

    @Override
    public void saveClient(Client client) {
        this.clientDao.saveClient(client);
    }

    @Override
    public void deleteClient(int id) {
        this.clientDao.deleteClientById(id);
    }

    @Override
    public Client getClientById(int id) {
        return this.clientDao.getClientById(id);
    }

    @Override
    public List<Client> getListClients() {
        return this.clientDao.getListClients();
    }

    @Override
    public void updateClient(Client newClient) {
        this.clientDao.updateClient(newClient);

    }

    @Override
    public void saveFoodAndActivity(int clientId, FoodAndActivity foodAndActivity) {
        this.clientDao.saveFoodAndActivity(clientId,foodAndActivity);
    }

    @Override
    public int getCalorieCalculationByClientId(int id) {
        return this.clientDao.getCalorieCalculationByClientId(id);
    }
}

