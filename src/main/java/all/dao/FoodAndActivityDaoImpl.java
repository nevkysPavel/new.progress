package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;


@Repository
@FieldDefaults(level = PRIVATE)
@Transactional
public class FoodAndActivityDaoImpl implements FoodAndActivityDao {

    final ClientDao clientDao;
    @PersistenceContext
    EntityManager em;

    @Autowired
    public FoodAndActivityDaoImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    //Get Food by id ?
//    @Override
//    public FoodAndActivity getFoodAndActivityById(int id) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<FoodAndActivity> criteriaQuery = cb.createQuery(FoodAndActivity.class);
//        Root<FoodAndActivity> root = criteriaQuery.from(FoodAndActivity.class);
//        criteriaQuery.select(root);
//        criteriaQuery.where(cb.equal(root.getFoodAndActivityByDateAndClientId("client_id"), id));
//        em.createQuery(criteriaQuery).getSingleResult();
//        return em.createQuery(criteriaQuery).getSingleResult();
//    }

//    //Get Food by date ?
//    @Override
//    public FoodAndActivity getFoodAndActivityByDate(String date) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<FoodAndActivity> clientQuery = cb.createQuery(FoodAndActivity.class);
//        Root<FoodAndActivity> root = clientQuery.from(FoodAndActivity.class);
//        clientQuery.select(root);
//        clientQuery.where(cb.equal(root.get("date"), date));
//        em.createQuery(clientQuery).getSingleResult();
//        return em.createQuery(clientQuery).getSingleResult();
//    }

    //Get All Food by Client id ?
    @Override
    public List<FoodAndActivity> getFoodAndActivityByIdClient(int id) {
        Client client = clientDao.getClientById(id);
        List<FoodAndActivity> allFoodAndActivity = client.getFoodAndActivities();
        return allFoodAndActivity;
    }

    //Save Food
    @Override
    public int saveFoodAndActivity(FoodAndActivity foodAndActivity) {
        em.persist(foodAndActivity);
        em.flush();
        return foodAndActivity.getId();
    }

    //Get Calories By Client id and Date ?
    //Fixme
//    @Override
//    public int getCaloricityByDateAndClientId(int id, LocalDate localDate) {
//        Client client = clientDao.getClientById(id);
//        client.getFoodAndActivities();
//        return 0;
//    }

    //Get Food by Client id and Date ? / дописать и проверка и тесты
    //FixMe
    @Override
    public FoodAndActivity getFoodAndActivityByDateAndClientId(int clientId, LocalDate foodAndActivityData) {
        Client clientById = clientDao.getClientById(clientId);
        List<FoodAndActivity> foodAndActivities = clientById.getFoodAndActivities();
        return foodAndActivities.stream()
                .filter(foodAndActivity -> foodAndActivity.getLocalDate().equals(foodAndActivityData))
                .findFirst()
                .orElseGet(FoodAndActivity::new);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
