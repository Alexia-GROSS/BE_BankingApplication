package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Client;
import nl.rabobank.banking_application.model.MessageResponse;
import nl.rabobank.banking_application.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;

    public MessageResponse createClient(Client client){
        Client newClient = new Client();
        newClient.setFirstName(client.getFirstName());
        newClient.setLastName(client.getLastName());
        newClient.setUserName(client.getUserName());
        newClient.setPassword(client.getPassword());
        clientRepository.save(newClient);
        return new MessageResponse("New client created successfully");
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
