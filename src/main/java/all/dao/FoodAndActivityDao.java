package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface FoodAndActivityDao {
    int getClientAge(Client client);

    List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id);

    void saveFoodAndActivityByIdClient(int id, FoodAndActivity foodAndActivity);

//    void saveFoodAndActivity(FoodAndActivity foodAndActivity);

    FoodAndActivity getFoodAndActivityById(int id);

    FoodAndActivity getFoodAncActivityByDate(String date);

    default int getThisYear(){
        return LocalDate.now().getYear();
    }

}

