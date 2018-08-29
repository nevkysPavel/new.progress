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


    @Override
    public List<FoodAndActivity> getFoodAndActivityByIdClient(int id) {
        Client client = clientDao.getClientById(id);
        return  client.getFoodAndActivities();
    }


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
