package all.service;

import all.entity.Client;

import java.util.List;

public interface ClientService {
    void saveClient(Client client);

    void deleteClient(int id);

    Client getClientById(int id);

    List<Client> getListClients();

    void updateClient(Client client, Client newClient);


}
