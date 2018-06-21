package all.service;

import all.dao.ClientDao;
import all.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@FieldDefaults(level = PRIVATE)
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
//    @Setter
            ClientDao clientDao;

    @Override
//    @Transactional(propagation = Propagation.NEVER)
    //Аннотация для управления транзакциями с помощью Spring
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

