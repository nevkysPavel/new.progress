package all.service;

import all.dao.ClientDao;
import all.entity.Client;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientDao clientDao;

    @Override
    public void saveClient(Client client) {
        this.clientDao.saveClient(client);
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
    public void updateClient(Client client, Client newClient) {
        this.clientDao.updateClient(client, newClient);

    }
}

