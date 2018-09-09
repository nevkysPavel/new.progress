package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;
import all.entity.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;


@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private FoodAndActivityDao foodAndActivityDao;

    //Save Client
    @Override
    public int saveClient(Client client) {
        em.persist(client);
        em.flush();
        return client.getClient_id();
    }

    //Delete Client
    @Override
    public void deleteClientById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Client> criteriaDelete = cb.createCriteriaDelete(Client.class);
        Root<Client> root = criteriaDelete.from(Client.class);
        criteriaDelete.where(cb.equal(root.get("client_id"), id));
        em.createQuery(criteriaDelete).executeUpdate();
    }

    //Get Client by id
    @Override
    public Client getClientById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> clientQuery = cb.createQuery(Client.class);
        Root<Client> clientRoot = clientQuery.from(Client.class);
        clientQuery.select(clientRoot);
        clientQuery.where(cb.equal(clientRoot.get("client_id"), id));
        em.createQuery(clientQuery).getSingleResult();
        return em.createQuery(clientQuery).getSingleResult();
    }

    //Get List of Clients
    @Override
    public List<Client> getListClients() {
        List<Client> allClient;
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> clientRoot = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(clientRoot);
        TypedQuery<Client> q = em.createQuery(all);
        allClient = q.getResultList();
        return allClient;

    }

    //Save FoodAndActivity to Client
    @Override
    public LocalDate saveFoodAndActivity(int clientId, FoodAndActivity foodAndActivity) {
        Client client = getClientById(clientId);
        foodAndActivity.setClient(client);
        foodAndActivityDao.saveFoodAndActivity(foodAndActivity);
        return foodAndActivity.getLocalDate();
        //em.persist(foodAndActivity);
    }

    @Override
    public Client putClient(Client newClient) {
        Client client = em.find(Client.class, newClient.getClient_id());
        client.setClient_id(newClient.getClient_id());
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        client.setSex(newClient.getSex());
        client.setYears(newClient.getYears());
        client.setHeight(newClient.getHeight());
        client.setWeight(newClient.getWeight());
        return em.merge(client);
    }

    @Override
    public int getClientAge(Client client) {
        return getThisYear() - client.getYears();
    }

    //Get Client Calorie
    //Fixme
    @Override
    public int getCalorieCalculationByClientId(int id) {
        Client client = getClientById(id);
        int i = (int) ((10 * client.getWeight()) + (6.25 * client.getHeight()) - (5 * getClientAge(client)));
        if (client.getSex() == Sex.MAN) {
            i += 5;
        } else {
            i -= 161;
        }
        return i;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}

