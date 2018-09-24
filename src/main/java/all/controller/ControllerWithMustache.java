package all.controller;


import all.dto.UpdateFoodAndActivityDTO;
import all.entity.Client;
import all.entity.FoodAndActivity;
import all.entity.KindOfSport;
import all.service.ClientService;
import all.service.FoodAndActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.ws.rs.QueryParam;
import java.time.LocalDate;

@Controller
public class ControllerWithMustache {

    private final ClientService clientService;
    private final FoodAndActivityService foodAndActivityService;

    @Autowired
    public ControllerWithMustache(ClientService clientService, FoodAndActivityService foodAndActivityService) {
        this.clientService = clientService;
        this.foodAndActivityService=foodAndActivityService;
    }


    @GetMapping("/newClient")
    public ModelAndView saveClient() {
        return new ModelAndView("index", "client", new Client());
    }

    @PostMapping("/savedClient")
    public ModelAndView saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return new ModelAndView("savedClient", "client", clientService.getClientById(client.getClient_id()));
    }

    @GetMapping("/getClientById")
    public ModelAndView getClientById(@QueryParam("id") Integer id) {
        return new ModelAndView("getClientById", "client", clientService.getClientById(id));
    }

    @GetMapping("/deleteClientById")
    public ModelAndView deleteClientById(Integer id) {
        clientService.deleteClient(id);
        return new ModelAndView("deleteClientById");
    }

    @GetMapping("/getListOfClients")
    public ModelAndView getListOfClients() {
        return new ModelAndView("getListOfClients", "client", clientService.getListClients());
    }

    @GetMapping("/clientAddFoodAndActivity")
    public ModelAndView clientAddFoodAndActivity() {
        return new ModelAndView("index", "client", new UpdateFoodAndActivityDTO());
    }


    @PostMapping("/saveFoodAndActivity")
    public ModelAndView saveFoodAndActivity(@RequestParam Integer clientId,
                                            @RequestParam String protein,
                                            @RequestParam String carbohydrate,
                                            @RequestParam String fat,
                                            @RequestParam String kindOfSport,
                                            @RequestParam Integer durationOfTraining) {
        FoodAndActivity foodAndActivity = new FoodAndActivity(Integer.valueOf(protein), Integer.valueOf(carbohydrate), Integer.valueOf(fat), KindOfSport.valueOf(kindOfSport), durationOfTraining, null);
        clientService.saveFoodAndActivity(clientId, foodAndActivity);
        return new ModelAndView("saveFoodAndActivity", "client",
                foodAndActivityService.getFoodAndActivityByDateAndClientId(clientId,LocalDate.now()));
    }


    @GetMapping("/updateClient")
    public ModelAndView updateClient() {
        return new ModelAndView("index", "client", new Client());
    }

    @PostMapping("/updatedClient")
    public ModelAndView updatedClient(@ModelAttribute Client client) {
        return new ModelAndView("updatedClient", "client", clientService.putClient(client));
    }

    @GetMapping("/calories")
    public ModelAndView calories(int id) {
        int i = clientService.getCalorieCalculationByClientId(id);
        return new ModelAndView("calories", "client", i);
    }

    @GetMapping("/getFoodAndActivityByIdClient")
    public ModelAndView getFoodAndActivityByIdClient(@QueryParam("id") Integer id){
        return new ModelAndView("getFoodAndActivityByIdClient","client",
                foodAndActivityService.getAllTablesFoodAndActivityByIdClient(id));
    }

    @GetMapping("/getFoodAndActivityByDateAndClientId")
    public ModelAndView getFoodAndActivityByDateAndClientId(@QueryParam("id") Integer id,
                                                            @QueryParam("year")Integer year,
                                                            @QueryParam("month") Integer month,
                                                            @QueryParam("day") Integer day){
        LocalDate foodAndActivityData = LocalDate.of(year,month,day);
        return new ModelAndView("getFoodAndActivityByDateAndClientId","client",
                foodAndActivityService.getFoodAndActivityByDateAndClientId(id,foodAndActivityData));
    }

    @GetMapping("/index")
    public ModelAndView returnToMainPage() {
        return new ModelAndView();
    }
}
