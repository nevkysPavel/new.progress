package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Transactional
@Repository
@FieldDefaults(level = PRIVATE)
public class FoodAndActivityDaoImpl implements FoodAndActivityDao {

    final ClientDao clientDao;
    @PersistenceContext
    @Setter
    EntityManager em;

    @Autowired
    public FoodAndActivityDaoImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public FoodAndActivity getFoodAndActivityById(int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FoodAndActivity> criteriaQuery = cb.createQuery(FoodAndActivity.class);
        Root<FoodAndActivity> root = criteriaQuery.from(FoodAndActivity.class);
        criteriaQuery.select(root);
        criteriaQuery.where(cb.equal(root.get("id"), id));
        em.createQuery(criteriaQuery).getSingleResult();
        return em.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public FoodAndActivity getFoodAncActivityByDate(String date) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FoodAndActivity> clientQuery = cb.createQuery(FoodAndActivity.class);
        Root<FoodAndActivity> root = clientQuery.from(FoodAndActivity.class);
        clientQuery.select(root);
        clientQuery.where(cb.equal(root.get("date"), date));
        em.createQuery(clientQuery).getSingleResult();
        return em.createQuery(clientQuery).getSingleResult();
    }

    @Override
    public List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id) {
        Client client = clientDao.getClientById(id);
        List<FoodAndActivity> allFoodAndActivity = client.getFoodAndActivities();
        return allFoodAndActivity;
    }

    @Override
    public void saveFoodAndActivity(FoodAndActivity foodAndActivity) {
        em.persist(foodAndActivity);
    }

    //Fixme
    @Override
    public int getCaloricityByDateAndClientId(int id, LocalDate localDate) {
        Client client = clientDao.getClientById(id);
        client.getFoodAndActivities();
        return 0;
    }
}
