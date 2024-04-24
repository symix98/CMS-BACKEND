package net.ccc.apps.core.service.dto;

import net.ccc.apps.core.domain.Attachement;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.AppUser} entity.
 */
public class AppUserDTO implements Serializable {



    @NotNull
    private String userId;

    private String name;

    private String email;

    private Boolean isAdministrator;

    private Boolean inactive;

    private Long attachmentId;

    private Attachement attachement;

    private Set<RoleDTO> roles = new HashSet<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(Boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Attachement getAttachement() {
        return attachement;
    }
    public void setAttachement(Attachement attachement) {
        this.attachement = attachement;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUserDTO)) {
            return false;
        }
        return userId != null && userId.equals(((AppUserDTO) o).userId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserDTO{" +
            ", userId='" + getUserId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", isAdministrator='" + getIsAdministrator() + "'" +
            ", inactive='" + getInactive() + "'" +
            ", roles=" + getRoles() +
            ", attachmentId='" + getAttachmentId()+ "'" +
            ", attachement='" + getAttachement()+ "'" +
            "}";
    }
}
