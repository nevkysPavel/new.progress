package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;

import java.time.LocalDate;

public interface FoodAndActivityDao {
    int getClientAge(Client client);

    int getCaloriesFromFood(int id, String date);

    FoodAndActivity getFoodAndActivityById(int id);

    FoodAndActivity getFoodAncActivityByDate(String date);

    default int getThisYear(){
        return LocalDate.now().getYear();
    }

}

