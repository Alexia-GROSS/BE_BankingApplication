package nl.rabobank.banking_application.service;

import nl.rabobank.banking_application.model.Client;
import nl.rabobank.banking_application.model.ClientPrinciple;
import nl.rabobank.banking_application.model.Role;
import nl.rabobank.banking_application.repository.ClientRepository;
import nl.rabobank.banking_application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = clientRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        Role roleClient = roleRepository.getClientRoles(username);

        Set<Role> rolesClient = new HashSet<>();
        rolesClient.add(roleClient);
        client.setRoles(rolesClient);

        //System.out.println(client.getRoles().size());
        return ClientPrinciple.build(client);
    }
}

