package all.dao;

import all.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/*@FieldDefaults(level = PRIVATE)
@Slf4j*/
@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {

    @PersistenceContext
    protected EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void saveClient(Client client) {

        em.persist(client);
    }

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
        em.createQuery(clientQuery)
                .getSingleResult();
        return  em.createQuery(clientQuery).getSingleResult();
    }

    @Override
    public List<Client> getListClients() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> clientRoot = cq.from(Client.class);
        CriteriaQuery<Client> all = cq.select(clientRoot);
        TypedQuery<Client> q = em.createQuery(all);
        List<Client> allClient = q.getResultList();

        return  allClient;

    }

    @Override
    public void updateClient(Client client, Client newClient) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Client> update = cb.createCriteriaUpdate(Client.class);
        Root e = update.from(Client.class);
        update.set("id",newClient);
        update.set("firstName",newClient);
        update.set("lastName", newClient);
        update.set("sex",newClient);
        update.set("years",newClient);
        update.set("height", newClient);
        update.set("weight", newClient);

        update.where(cb.equal(e.get("id"),client));
        update.where(cb.equal(e.get("firstName"), client));
        update.where(cb.equal(e.get("lastName"), client));
        update.where(cb.equal(e.get("sex"),client));
        update.where(cb.equal(e.get("years"),client));
        update.where(cb.equal(e.get("height"),client));
        update.where(cb.equal(e.get("weight"),client));

        this.em.createQuery(update).executeUpdate();

    }
}

