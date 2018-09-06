package all.controller;


import all.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class ControllerWithMustache {
    final ClientService clientService;
    @Autowired
    public ControllerWithMustache(ClientService clientService){
        this.clientService=clientService;
    }


    @RequestMapping(value = "/s")
    public String saveClient(Model model){
        model.addAttribute("client",clientService.getClientById(4));
        return "all";
    }

}
