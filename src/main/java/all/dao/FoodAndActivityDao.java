package all.dao;

import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface FoodAndActivityDao {

    FoodAndActivity getFoodAndActivityById(int id);

    FoodAndActivity getFoodAncActivityByDate(String date);

    List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id);

    void saveFoodAndActivity(FoodAndActivity foodAndActivity);

    int getCaloricityByDateAndClientId(int id, LocalDate localDate);

}

