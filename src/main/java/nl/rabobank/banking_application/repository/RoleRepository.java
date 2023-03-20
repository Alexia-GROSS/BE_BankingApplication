package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Role;
import nl.rabobank.banking_application.model.RoleName;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByName(RoleName roleName);

    void save(long clientID, Role role);
}
