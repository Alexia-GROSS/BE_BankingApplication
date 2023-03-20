package nl.rabobank.banking_application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleid;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleName rolename;

    public Role() {}

    public Role(RoleName rolename) {
        this.rolename = rolename;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public RoleName getRolename() {
        return rolename;
    }

    public void setRolename(RoleName rolename) {
        this.rolename = rolename;
    }
}
