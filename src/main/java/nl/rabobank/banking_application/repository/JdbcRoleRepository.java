package nl.rabobank.banking_application.repository;

import nl.rabobank.banking_application.model.Role;
import nl.rabobank.banking_application.model.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class JdbcRoleRepository implements RoleRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Role> findByName(RoleName roleName) {
        Role roleByName = jdbcTemplate.queryForObject("SELECT * FROM roles WHERE name=?",
                new RoleMapper(), roleName.toString());
        assert roleByName != null;
        return Optional.of(
                roleByName
        );
    }

    @Override
    public void save(long clientID, Role role) {
        jdbcTemplate.update("INSERT INTO user_roles (user_id, role_id) VALUES(?,?)",
                clientID, role.getRoleid());
    }

    @Override
    public Role getClientRoles(String username) {
        String sql = "SELECT roles.roleid, roles.name from clients, user_roles, roles WHERE clients.username=? AND user_roles.user_id = clients.clientid AND user_roles.role_id = roles.roleid";
        Role clientRoles = jdbcTemplate.queryForObject(sql, new Object[]{username}, new RoleMapper());
        System.out.println(clientRoles);
        assert clientRoles != null;
        return clientRoles;
    }
}

class RoleMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setRoleid(rs.getLong("roleid"));
        if (rs.getString("name") == "ROLE_USER"){
            role.setRolename(RoleName.ROLE_USER);
        } else if (rs.getString("name") == "ROLE_PM") {
            role.setRolename(RoleName.ROLE_PM);
        } else{
            role.setRolename(RoleName.ROLE_ADMIN);
        }
        return role;
    }

}
