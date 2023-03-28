package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class JdbcClientRepository implements ClientRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Client> findByUsername(String username) {
        Client clientByUsername = jdbcTemplate.queryForObject("SELECT * FROM clients WHERE clients.username=?",
                new Object[]{username}, new ClientMapper());
        assert clientByUsername != null;
        return Optional.of(
                clientByUsername
        );
    }

    @Override
    public Optional<Client> findByUsernameWithRoleName(String username) {
        Client clientByUsername = jdbcTemplate.queryForObject("SELECT clientid, clients.name, username, email, password, roles.name from clients, user_roles, roles WHERE clients.username=? AND user_roles.user_id = clients.clientid AND user_roles.role_id = roles.roleid",
                new Object[]{username}, new ClientMapper());
        assert clientByUsername != null;
        return Optional.of(
                clientByUsername
        );
    }

    @Override
    public Boolean existsByUsername(String username) {
        try {
            Client clientByUsername = jdbcTemplate.queryForObject("SELECT * FROM clients WHERE clients.username=?",
                    new Object[]{username}, Client.class);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public Boolean existsByEmail(String email) {
        try {
            Client clientByEmail = jdbcTemplate.queryForObject("SELECT * FROM clients WHERE clients.email=?",
                    new ClientMapper(), email);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void save(Client newClient) {
        jdbcTemplate.update("INSERT INTO clients (name, username, email, password) VALUES(?,?,?,?)",
                newClient.getName(), newClient.getUsername(), newClient.getEmail(), newClient.getPassword());
    }

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query("SELECT * from clients", new ClientMapper());
    }

    @Override
    public int getClientId() {
        String sql = "SELECT clientid from clients order by clientid desc limit 1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}

class ClientMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();

        client.setClientID(rs.getInt("clientid"));
        client.setName(rs.getString("name"));
        client.setUsername(rs.getString("username"));
        client.setEmail(rs.getString("email"));
        client.setPassword(rs.getString("password"));

        return client;
    }
}

class ClientMapperRole implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        Role role = new Role();

        client.setClientID(rs.getInt("clientid"));
        client.setName(rs.getString("name"));
        client.setUsername(rs.getString("username"));
        client.setEmail(rs.getString("email"));
        client.setPassword(rs.getString("password"));
        role.setRoleid(rs.getLong("roleid"));
        if (rs.getString("name") == "ROLE_USER"){
            role.setRolename(RoleName.ROLE_USER);
        } else if (rs.getString("name") == "ROLE_PM") {
            role.setRolename(RoleName.ROLE_PM);
        } else{
            role.setRolename(RoleName.ROLE_ADMIN);
        }
        System.out.println(role.getRolename());
        client.setRoles((Set<Role>) role);

        return client;
    }
}