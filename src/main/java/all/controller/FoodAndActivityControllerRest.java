package all.controller;

import all.entity.FoodAndActivity;
import all.service.FoodAndActivityService;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("food/")
@FieldDefaults(level = PRIVATE)
public class FoodAndActivityControllerRest {

    @Getter
    final FoodAndActivityService foodAndActivityService;

    @Autowired
    public FoodAndActivityControllerRest(FoodAndActivityService foodAndActivityService) {
        this.foodAndActivityService = foodAndActivityService;
    }

/*    @GetMapping(value = "getFoodAndActivityByDateAndClientId/getAllTablesFoodAndActivityByIdClient/{client_id}")
    public ResponseEntity<List<FoodAndActivity>> getAllTablesFoodAndActivityByIdClient(@PathVariable("client_id") int client_id) {
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<FoodAndActivity> foodAndActivityList = this.foodAndActivityService.getAllTablesFoodAndActivityByIdClient(client_id);
        if(Objects.nonNull(foodAndActivityList)){
            responseEntity = new ResponseEntity(foodAndActivityList,HttpStatus.FOUND);
        }
        return responseEntity;
    }*/

    @GetMapping(value = "by_local_date")
    public FoodAndActivity getFoodAndActivityByDateAndClientId(@RequestParam int client_id, @RequestParam LocalDate date) {

        return foodAndActivityService.getFoodAndActivityByDateAndClientId(client_id, date);
    }
}
