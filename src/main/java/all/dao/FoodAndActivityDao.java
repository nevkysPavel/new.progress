package all.dao;

import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface FoodAndActivityDao {

//    FoodAndActivity getFoodAndActivityById(int id);

//    FoodAndActivity getFoodAndActivityByDate(String date);

    List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id);

    int saveFoodAndActivity(FoodAndActivity foodAndActivity);

//    int getCaloricityByDateAndClientId(int id, LocalDate localDate);

    FoodAndActivity getFoodAndActivityByDateAndClientId(int clientId, LocalDate foodAndActivityData);
}

