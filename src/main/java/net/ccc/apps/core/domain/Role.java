package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Role.
 */
@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<AppUser> appUsers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getRoleId() {
        return roleId;
    }

    public Role roleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public Role description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AppUser> getAppUsers() {
        return appUsers;
    }

    public Role appUser(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
        return this;
    }

    public void setAppUsers(Set<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return roleId != null && roleId.equals(((Role) o).roleId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Role{" +
            ", roleId='" + getRoleId() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
