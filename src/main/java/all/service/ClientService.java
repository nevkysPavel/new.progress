package all.service;

import all.entity.Client;
import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {
    int saveClient(Client client);

    int deleteClient(int id);

    Client getClientById(int id);

    List<Client> getListClients();

    Client putClient(Client newClient);

    LocalDate saveFoodAndActivity(int clientId, FoodAndActivity foodAndActivity);

    int getCalorieCalculationByClientId(int id);

}
