package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Client;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientService {

    List<Client> getAllClient();
}
