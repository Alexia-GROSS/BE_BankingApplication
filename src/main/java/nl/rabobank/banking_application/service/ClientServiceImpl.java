package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Client;
import nl.rabobank.banking_application.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements  ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> getAllClient() {
        return clientRepository.findAll();
    }
}
