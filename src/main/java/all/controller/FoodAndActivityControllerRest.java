//package all.controller;
//
//import all.entity.FoodAndActivity;
//import all.service.FoodAndActivityService;
//import lombok.Getter;
//import lombok.experimental.FieldDefaults;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Objects;
//
//import static lombok.AccessLevel.PRIVATE;
//@RestController
//@RequestMapping("food/")
//@FieldDefaults(level = PRIVATE)
//public class FoodAndActivityControllerRest {
//
//    @Getter
//    final FoodAndActivityService foodAndActivityService;
//
//    @Autowired
//    public FoodAndActivityControllerRest(FoodAndActivityService foodAndActivityService){
//        this.foodAndActivityService=foodAndActivityService;
//    }
//
//    @GetMapping(value = "get/getAllTablesFoodAndActivityByIdClient/{id}")
//    public ResponseEntity<List<FoodAndActivity>> getAllTablesFoodAndActivityByIdClient(@PathVariable("id") int id) {
//        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        List<FoodAndActivity> foodAndActivityList = this.foodAndActivityService.getAllTablesFoodAndActivityByIdClient(id);
//        if(Objects.nonNull(foodAndActivityList)){
//            responseEntity = new ResponseEntity(foodAndActivityList,HttpStatus.FOUND);
//        }
//        return responseEntity;
//    }
//}
