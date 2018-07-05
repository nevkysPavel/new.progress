package all.dao;

import all.entity.Client;
import all.entity.FoodAndActivity;

import java.time.LocalDate;
import java.util.List;

public interface FoodAndActivityDao {
    int getClientAge(Client client);

    FoodAndActivity getFoodAndActivityById(int id);

    FoodAndActivity getFoodAncActivityByDate(String date);

    List<FoodAndActivity> getAllTablesFoodAndActivityByIdClient(int id);

    void saveFoodAndActivity(FoodAndActivity foodAndActivity);

    default int getThisYear() {
        return LocalDate.now().getYear();
    }

}

