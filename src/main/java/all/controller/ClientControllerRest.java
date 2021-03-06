package all.controller;

import all.dto.UpdateFoodAndActivityDTO;
import all.entity.Client;
import all.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("food/api/clients")
public class ClientControllerRest {

    private final ClientService clientService;

    @Autowired
    public ClientControllerRest(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/get/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") int id) {
        Client client = this.clientService.getClientById(id);
        ResponseEntity<Client> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (Objects.nonNull(client)) {
            responseEntity = new ResponseEntity<>(client, HttpStatus.OK);
        }
        return responseEntity;
    }

    @PostMapping(value = "/post/client")
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {
        ResponseEntity<Client> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (Objects.nonNull(client)) {
            responseEntity = new ResponseEntity<>(client, HttpStatus.CREATED);
            this.clientService.saveClient(client);
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/delete/client/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") int id) {
        Client client = this.clientService.getClientById(id);
        ResponseEntity<Client> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (Objects.nonNull(client)) {
            this.clientService.deleteClient(id);
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @GetMapping(value = "/get/clients")
    public ResponseEntity<List<Client>> getListClients() {
        ResponseEntity<List<Client>> responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<Client> clientList = this.clientService.getListClients();
        if (Objects.nonNull(clientList)) {
            responseEntity = new ResponseEntity<>(clientList, HttpStatus.FOUND);
        }
        return responseEntity;
    }

    @PutMapping(value = "/put/client")
    public ResponseEntity<Client> updateClient(@RequestBody @Valid Client newClient) {
        ResponseEntity<Client> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (Objects.nonNull(newClient)) {
            responseEntity = new ResponseEntity<>(this.clientService.putClient(newClient), HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @PostMapping(value = "/post/foodAndActivity")
    public ResponseEntity saveFoodAndActivity(@RequestBody @Valid UpdateFoodAndActivityDTO updateFoodAndActivityDTO) {
        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (Objects.nonNull(updateFoodAndActivityDTO)) {
            clientService.saveFoodAndActivity(updateFoodAndActivityDTO.getClientId(), updateFoodAndActivityDTO.getFoodAndActivity());
            responseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @GetMapping(value = "/get/calorie/{id}")
    public ResponseEntity<Integer> getCalorieCalculationByClientId(@PathVariable("id") int clientId) {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        int amount = this.clientService.getCalorieCalculationByClientId(clientId);
        if (amount != 0) {
            responseEntity = new ResponseEntity<>(amount, HttpStatus.OK);
        }
        return responseEntity;
    }

}