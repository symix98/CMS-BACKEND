package net.ccc.apps.core.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A StorageService.
 */
@Entity
@Table(name = "storage_service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StorageService implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "service_id")
    private String serviceId;

    @Column(name = "secret")
    private String secret;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "name")
    private String name;

    @Column(name = "default_value")
    private Boolean defaultValue;

    public String getServiceId() {
        return serviceId;
    }

    public StorageService serviceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getSecret() {
        return secret;
    }

    public StorageService secret(String secret) {
        this.secret = secret;
        return this;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHostname() {
        return hostname;
    }

    public StorageService hostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getName() {
        return name;
    }

    public StorageService name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDefaultValue() {
        return defaultValue;
    }

    public StorageService defaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public void setDefaultValue(Boolean defaultValue) {
        this.defaultValue = defaultValue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StorageService)) {
            return false;
        }
        return serviceId != null && serviceId.equals(((StorageService) o).serviceId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageService{" +
            " serviceId='" + getServiceId() + "'" +
            ", secret='" + getSecret() + "'" +
            ", hostname='" + getHostname() + "'" +
            ", name='" + getName() + "'" +
            ", defaultValue='" + isDefaultValue() + "'" +
            "}";
    }
}
