package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.Role} entity.
 */
public class RoleDTO implements Serializable {

    @NotNull
    private String roleId;

    private String description;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoleDTO)) {
            return false;
        }

        return roleId != null && roleId.equals(((RoleDTO) o).roleId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RoleDTO{" +
            ", roleId='" + getRoleId() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
