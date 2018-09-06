package all.service;

import all.dao.FoodAndActivityDao;
import all.entity.FoodAndActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class FoodAndActivityServiceImpl implements FoodAndActivityService{
    final FoodAndActivityDao foodAndActivityDao;

    @Autowired
    public FoodAndActivityServiceImpl(FoodAndActivityDao foodAndActivityDao){
        this.foodAndActivityDao=foodAndActivityDao;
    }

    @Override
    public List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id){
        return this.foodAndActivityDao.getFoodAndActivityByIdClient(id);
    }

//    @Override
//    public FoodAndActivity getFoodAndActivityById(int id) {
//        return foodAndActivityDao.getFoodAndActivityById(id);
//    }

    @Override
    public FoodAndActivity getFoodAndActivityByDateAndClientId(int clientId, LocalDate foodAndActivityData){
        return this.foodAndActivityDao.getFoodAndActivityByDateAndClientId(clientId,foodAndActivityData);}
}
