package nl.rabobank.banking_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.rabobank.banking_application.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
