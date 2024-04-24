package net.ccc.apps.core.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
/**
 * A StorageServiceParameter.
 */
@Entity
@Table(name = "storage_service_parameter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class StorageServiceParameter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "service_id", nullable = false)
    private String serviceId;

    @NotNull
    @Column(name = "param_name", nullable = false)
    private String paramName;

    @NotNull
    @Column(name = "param_value", nullable = false)
    private String paramValue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public StorageServiceParameter serviceId(String serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getParamName() {
        return paramName;
    }

    public StorageServiceParameter paramName(String paramName) {
        this.paramName = paramName;
        return this;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public StorageServiceParameter paramValue(String paramValue) {
        this.paramValue = paramValue;
        return this;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StorageServiceParameter)) {
            return false;
        }
        return id != null && id.equals(((StorageServiceParameter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageServiceParameter{" +
            "id=" + getId() +
            ", serviceId='" + getServiceId() + "'" +
            ", paramName='" + getParamName() + "'" +
            ", paramValue='" + getParamValue() + "'" +
            "}";
    }
}
