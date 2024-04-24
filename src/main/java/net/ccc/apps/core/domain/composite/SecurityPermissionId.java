package net.ccc.apps.core.domain.composite;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SecurityPermissionId implements Serializable {
    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Column(name = "object", nullable = false)
    private String object;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }


    @Override
    public String toString() {
        return "PermissionId{" +
            "role='" + role + '\'' +
            ", object='" + object + '\'' +
            '}';
    }
}
