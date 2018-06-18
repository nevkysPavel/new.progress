package all.dao;

import all.entity.Client;
import org.hibernate.Criteria;
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

 /*   @PersistenceUnit
    EntityManagerFactory factory;*/

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

////        log.info("Client loaded. Client details: " + client.toString());


    }

    @Override
    public List<Client> getListClients() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Client> criteriaQuery = cb.createQuery(Client.class);

        TypedQuery<Client> query = em.createQuery(criteriaQuery);

        List<Client> allClient = query.getResultList();

        return  allClient;

    }

    @Override
    public void updateClient(Client client) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//
//        CriteriaUpdate<Client> update = cb.createCriteriaUpdate(Client.class);
//        Root<Client> root = update.from(Client.class);
        em.merge(client);
    }
}

