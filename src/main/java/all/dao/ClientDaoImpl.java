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
    private EntityManager em;

    @Autowired
    private FoodAndActivityDao foodAndActivityDao;

    @Override
    public int saveClient(Client client) {
        em.persist(client);
        em.flush();
        return client.getClient_id();
    }

    @Override
    public void deleteClientById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Client> criteriaDelete = cb.createCriteriaDelete(Client.class);
        Root<Client> root = criteriaDelete.from(Client.class);
        criteriaDelete.where(cb.equal(root.get("client_id"), id));
        em.createQuery(criteriaDelete).executeUpdate();
    }

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

    @Override
    public LocalDate saveFoodAndActivity(int clientId, FoodAndActivity foodAndActivity) {
        Client client = getClientById(clientId);
        foodAndActivity.setClient(client);
        foodAndActivityDao.saveFoodAndActivity(foodAndActivity);
        return foodAndActivity.getLocalDate();
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

    @Override
    public int getCalorieCalculationByClientId(int id) {
        Client client = getClientById(id);
        int caloricity = (int) ((10 * client.getWeight()) + (6.25 * client.getHeight()) - (5 * getClientAge(client)));
        if (client.getSex() == Sex.MAN || client.getSex() == Sex.TRANSEXUAL || client.getSex() == Sex.TRANSGENDER) {
            caloricity += 5;
        } else {
            caloricity -= 161;
        }
        return caloricity;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}

