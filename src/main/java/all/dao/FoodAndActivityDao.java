package all.dao;

import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface FoodAndActivityDao {

    List<FoodAndActivity> getFoodAndActivityByIdClient(int id);

    int saveFoodAndActivity(FoodAndActivity foodAndActivity);

    FoodAndActivity getFoodAndActivityByDateAndClientId(int clientId, LocalDate foodAndActivityData);
}

