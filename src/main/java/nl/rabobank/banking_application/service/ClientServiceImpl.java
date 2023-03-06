package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Client;
import nl.rabobank.banking_application.model.MessageResponse;
import nl.rabobank.banking_application.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Override
    public MessageResponse createClient(Client client) {
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return null;
    }
}
