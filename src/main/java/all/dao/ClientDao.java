package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface ClientDao {
    void saveClient(Client client);

    void deleteClientById(int id);

    Client getClientById(int id);

    List<Client> getListClients();

    void updateClient(Client newClient);

    void saveFoodAndActivity(int clientId,FoodAndActivity foodAndActivity);

    default int getThisYear() {
        return LocalDate.now().getYear();
    }

     int getClientAge(Client client);

    int getCalorieCalculationByClientId(int id);

    int getCaloriesByDateAndClientId(int id, LocalDate localDate);



}
