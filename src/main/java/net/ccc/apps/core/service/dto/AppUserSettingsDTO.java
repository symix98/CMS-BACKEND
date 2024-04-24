package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.AppUserSettings} entity.
 */
public class AppUserSettingsDTO implements Serializable {

    private Long id;

    @NotNull
    private String property;

    @NotNull
    private String value;

    private AppUserDTO appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AppUserDTO getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserDTO appUser) {
        this.appUser = appUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUserSettingsDTO)) {
            return false;
        }

        AppUserSettingsDTO appUserSettingsDTO = (AppUserSettingsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, appUserSettingsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserSettingsDTO{" +
            "id=" + getId() +
            ", property='" + getProperty() + "'" +
            ", value='" + getValue() + "'" +
            ", appUser=" + getAppUser() +
            "}";
    }
}
