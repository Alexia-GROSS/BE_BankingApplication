package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Client;
import nl.rabobank.banking_application.model.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ClientService {
    MessageResponse createClient(Client client);
   // Client getASingleClient(Long clientID);
    List<Client> getAllClients();
    //Optional<Client> updateClient(Long clientID, Client client);
    //Optional<Client> deleteClient(Long clientID);
}
