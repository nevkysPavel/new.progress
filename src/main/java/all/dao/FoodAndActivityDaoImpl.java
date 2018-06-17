package all.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


@Repository
public class FoodAndActivityDaoImpl implements FoodAndActivityDao {
    //    static final Logger logger = LoggerFactory.getLogger(FoodAndActivityDao.class);
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Float getCalories(FoodAndActivityDao foodAndActivityDao) {
        Session session = this.sessionFactory.getCurrentSession();

        return null;
    }
}
