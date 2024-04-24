package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;

import net.ccc.apps.core.domain.composite.SecurityPermissionId;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SecurityPermission.
 */
@Entity
@Table(name = "security_permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SecurityPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SecurityPermissionId id;

    @Column(name = "permission")
    private String permission;

    public String getPermission() {
        return permission;
    }
    public void setPermission(String permission) {
        this.permission = permission;
    }
    public SecurityPermissionId getId() {
        return id;
    }
    public void setId(SecurityPermissionId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecurityPermission)) {
            return false;
        }
        return id != null && id.equals(((SecurityPermission) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Permissions{" +
            " permissionId=" + getId() +
            ", permission='" + getPermission() + "'" +
            "}";
    }
}
