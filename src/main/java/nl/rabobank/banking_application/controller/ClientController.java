package nl.rabobank.banking_application.controller;

import nl.rabobank.banking_application.model.Category;
import nl.rabobank.banking_application.model.Client;
import nl.rabobank.banking_application.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClient (){
        List<Client> clients = clientService.getAllClient();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

}