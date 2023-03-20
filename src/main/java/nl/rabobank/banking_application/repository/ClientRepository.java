package nl.rabobank.banking_application.repository;
import nl.rabobank.banking_application.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Optional<Client> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    void save(Client user);

    List<Client> findAll();

    int getClientId();
}
