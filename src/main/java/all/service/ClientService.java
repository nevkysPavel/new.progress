package all.service;

import all.entity.Client;

import java.util.List;

public interface ClientService {
    void addClient(Client client);

    void deleteClient(int id);

    Client getClientById(int id);

    List<Client> getListClients();
}
