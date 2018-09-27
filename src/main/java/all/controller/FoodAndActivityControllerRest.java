package all.controller;

import all.entity.FoodAndActivity;
import all.service.FoodAndActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("food/")
public class FoodAndActivityControllerRest {

    private final FoodAndActivityService foodAndActivityService;

    @Autowired
    public FoodAndActivityControllerRest(FoodAndActivityService foodAndActivityService) {
        this.foodAndActivityService = foodAndActivityService;
    }

    @GetMapping(value = "getFoodAndActivityByIdClient/{client_id}")
    public ResponseEntity<List<FoodAndActivity>> getAllTablesFoodAndActivityByIdClient(@PathVariable("client_id") int client_id) {
        ResponseEntity<List<FoodAndActivity>> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<FoodAndActivity> foodAndActivityList = this.foodAndActivityService.getAllTablesFoodAndActivityByIdClient(client_id);
        if (Objects.nonNull(foodAndActivityList)) {
            responseEntity = new ResponseEntity<>(foodAndActivityList, HttpStatus.FOUND);
        }
        return responseEntity;
    }

    @GetMapping(value = "by_local_date")
    public FoodAndActivity getFoodAndActivityByDateAndClientId(@RequestParam int client_id, @RequestParam LocalDate date) {
        return foodAndActivityService.getFoodAndActivityByDateAndClientId(client_id, date);
    }

}
