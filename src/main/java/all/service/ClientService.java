package all.service;

import all.entity.Client;
import all.entity.FoodAndActivity;

import java.util.List;

public interface ClientService {
    int saveClient(Client client);

    void deleteClient(int id);

    Client getClientById(int id);

    List<Client> getListClients();

    Client putClient(Client newClient);

    void saveFoodAndActivity(int clientId,FoodAndActivity foodAndActivity);

    int getCalorieCalculationByClientId(int id);

}
