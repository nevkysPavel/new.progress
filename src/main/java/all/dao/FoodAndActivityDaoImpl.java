package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;


@Repository

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
