package all.dao;

import all.entity.Client;

import java.util.List;

public interface ClientDao {
    void addClient(Client client);
    void deleteClientById(int id);
    Client getClientById(int id);
    List<Client> getListClients();

}
