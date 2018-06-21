package all.dao;

import all.entity.Client;

import java.util.List;

public interface ClientDao {
    void saveClient(Client client);
    void deleteClientById(int id);
    Client getClientById(int id);
    List<Client> getListClients();
    void updateClient(Client client, Client newClient);

}
