package all.dao;

import all.entity.Client;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Repository
@Transactional
@FieldDefaults(level = PRIVATE)
public class ClientDaoImpl implements ClientDao {

    @PersistenceContext
    @Setter
    EntityManager em;


    @Override
    public void saveClient(Client client) {
        em.persist(client);
    }

    @Override
    public void deleteClientById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Client> criteriaDelete = cb.createCriteriaDelete(Client.class);
        Root<Client> root = criteriaDelete.from(Client.class);
        criteriaDelete.where(cb.equal(root.get("id"), id));
        em.createQuery(criteriaDelete).executeUpdate();

    }

    @Override
    public Client getClientById(int id) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> clientQuery = cb.createQuery(Client.class);
        Root<Client> clientRoot = clientQuery.from(Client.class);
        clientQuery.select(clientRoot);
        clientQuery.where(cb.equal(clientRoot.get("id"), id));
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


    // I chose update, because "Hibernate copies the attribute values of the detached entity to the managed entity.
    // This overwrites any changes that you performed on this entity within the current Session.
    //FixMe
    @Override
    public void updateClient(Client newClient) {
//        CriteriaBuilder cb = this.em.getCriteriaBuilder();
//        CriteriaUpdate<Order> update = cb.createCriteriaUpdate(Order.class);
//        Root e = update.from(Order.class);
//        update.set("id", newClient);
//        update.set("firstName", newClient);
//        update.set("lastName", newClient);
//        update.set("sex", newClient);
//        update.set("years", newClient);
//        update.set("height", newClient);
//        update.set("weight", newClient);
//
//        update.where(cb.equal(e.get("id"), client));
//        update.where(cb.equal(e.get("firstName"), client));
//        update.where(cb.equal(e.get("lastName"), client));
//        update.where(cb.equal(e.get("sex"), client));
//        update.where(cb.equal(e.get("years"), client));
//        update.where(cb.equal(e.get("height"), client));
//        update.where(cb.equal(e.get("weight"), client));
//
//        this.em.createQuery(update).executeUpdate();
//        int id = newClient.getId();
//        Client client = em.find(Client.class, id);
//        client.setId(newClient.getId());
//        client.setFirstName(newClient.getFirstName());
//        client.setLastName(newClient.getFirstName());
//        client.setSex(newClient.getSex());
//        client.setYears(newClient.getYears());
//        client.setHeight(newClient.getHeight());
//        client.setWeight(newClient.getWeight());
//        em.merge(client);

        Client client = em.find(Client.class,newClient.getId());
        client.setId(newClient.getId());
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getFirstName());
        client.setSex(newClient.getSex());
        client.setYears(newClient.getYears());
        client.setHeight(newClient.getHeight());
        client.setWeight(newClient.getWeight());
        em.merge(client);






   }
}

