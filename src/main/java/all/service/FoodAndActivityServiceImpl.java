package all.service;

import all.dao.FoodAndActivityDao;
import all.entity.FoodAndActivity;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Service

public class FoodAndActivityServiceImpl implements FoodAndActivityService{
    final FoodAndActivityDao foodAndActivityDao;

    @Autowired
    public FoodAndActivityServiceImpl(FoodAndActivityDao foodAndActivityDao){
        this.foodAndActivityDao=foodAndActivityDao;
    }

    @Override
    public List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id){
        return this.foodAndActivityDao.getAllTablesFoodAndActivityByIdClient(id);
    }

    @Override
    public FoodAndActivity getFoodAndActivityById(int id) {
        return foodAndActivityDao.getFoodAndActivityById(id);
    }
}
