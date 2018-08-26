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

    void saveFoodAndActivity(int clientId, FoodAndActivity foodAndActivity);

    Client putClient(Client newClient);


    //Fixme
    //Так можно ?
    default int getThisYear() {
        return LocalDate.now().getYear();
    }

    //Fixme
    //Так можно ?
    int getClientAge(Client client);

    int getCalorieCalculationByClientId(int id);


}
