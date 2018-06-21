package all.controller;

import all.entity.Client;
import all.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


//@FieldDefaults(level = PRIVATE)
@RestController
@RequestMapping("food/api/clients")
public class ClientControllerRest {

    @Autowired
    ClientService clientService;

    @GetMapping(value = "/get/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable("id") int clientId) {

        Client client = this.clientService.getClientById(clientId);

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (Objects.nonNull(client)) {
            responseEntity = new ResponseEntity<>(client, HttpStatus.OK);
        }

        return responseEntity;

    }

    @PostMapping(value = "/post/client")
    public ResponseEntity<Client> saveClient(@RequestBody @Valid Client client) {

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (Objects.nonNull(client)) {
            responseEntity = new ResponseEntity<>(client, HttpStatus.CREATED);
            this.clientService.saveClient(client);
        }

        return responseEntity;
    }


    @DeleteMapping(value = "/delete/client/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") int id) {

        Client client = this.clientService.getClientById(id);

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (Objects.nonNull(client)) {
            this.clientService.deleteClient(id);
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }


    @GetMapping(value = "/get/clients")
    public ResponseEntity<List<Client>> getListClients() {

        ResponseEntity responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        List<Client> clientList = this.clientService.getListClients();

        if(Objects.nonNull(clientList)){
            responseEntity = new ResponseEntity(clientList, HttpStatus.FOUND);
        }
        return responseEntity;
    }

    @PutMapping(value = "/put/client")
    public ResponseEntity<Client> updateClient(@RequestBody @Valid Client client, Client newClient){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);

        if(Objects.nonNull(client)){
            this.clientService.updateClient(client, newClient);
            responseEntity = new ResponseEntity(HttpStatus.CREATED);
        }
        return responseEntity;
    }

    public ClientService getClientService() {
        return clientService;
    }


}
