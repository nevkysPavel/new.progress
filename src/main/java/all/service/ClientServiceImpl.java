package all.service;

import all.dao.ClientDao;
import all.entity.Client;
import all.entity.FoodAndActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public int saveClient(Client client) {
        return clientDao.saveClient(client);
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
    public Client putClient(Client newClient) {
        return this.clientDao.putClient(newClient);

    }

    @Override
    public LocalDate saveFoodAndActivity(int clientId, FoodAndActivity foodAndActivity) {
        return this.clientDao.saveFoodAndActivity(clientId, foodAndActivity);
    }

    @Override
    public int getCalorieCalculationByClientId(int id) {
        return this.clientDao.getCalorieCalculationByClientId(id);
    }
}

