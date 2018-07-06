package all.service;


import all.entity.FoodAndActivity;

import java.util.List;

public interface FoodAndActivityService {
    List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id);

     FoodAndActivity getFoodAndActivityById(int id);

}
