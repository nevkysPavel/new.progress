package all.controller;

import all.service.FoodAndActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerFoodAndActivityWithMustache {
    private final FoodAndActivityService foodAndActivityService;
    public ControllerFoodAndActivityWithMustache(FoodAndActivityService foodAndActivityService){
        this.foodAndActivityService=foodAndActivityService;
    }

    @GetMapping("/getFoodAndActivityByIdClient")
    public ModelAndView getFoodAndActivityByIdClient(int id){
        return new ModelAndView("getFoodAndActivityByIdClient",
                "client",foodAndActivityService.getAllTablesFoodAndActivityByIdClient(id));
    }
}
