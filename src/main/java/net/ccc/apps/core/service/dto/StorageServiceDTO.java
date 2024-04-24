package net.ccc.apps.core.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.StorageService} entity.
 */
public class StorageServiceDTO implements Serializable {

    private String serviceId;

    private String secret;

    private String hostname;

    private String name;

    private Boolean defaultValue;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StorageServiceDTO)) {
            return false;
        }

        return serviceId != null && serviceId.equals(((StorageServiceDTO) o).serviceId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageServiceDTO{" +
             ", serviceId='" + getServiceId() + "'" +
            ", secret='" + getSecret() + "'" +
            ", hostname='" + getHostname() + "'" +
            ", name='" + getName() + "'" +
            ", defaultValue='" + isDefaultValue() + "'" +
            "}";
    }
}
