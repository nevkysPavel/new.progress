package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface ClientDao {
    int saveClient(Client client);

    void deleteClientById(int id);

    Client getClientById(int id);

    List<Client> getListClients();

    LocalDate saveFoodAndActivity(int clientId, FoodAndActivity foodAndActivity);

    Client putClient(Client newClient);

    default int getThisYear() {
        return LocalDate.now().getYear();
    }

    int getClientAge(Client client);

    int getCalorieCalculationByClientId(int id);

}
