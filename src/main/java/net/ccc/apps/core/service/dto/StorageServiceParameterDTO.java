package net.ccc.apps.core.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.StorageServiceParameter} entity.
 */
public class StorageServiceParameterDTO implements Serializable {

    private Long id;

    @NotNull
    private String serviceId;

    @NotNull
    private String paramName;

    @NotNull
    private String paramValue;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StorageServiceParameterDTO)) {
            return false;
        }

        return id != null && id.equals(((StorageServiceParameterDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageServiceParameterDTO{" +
            "id=" + getId() +
            ", serviceId='" + getServiceId() + "'" +
            ", paramName='" + getParamName() + "'" +
            ", paramValue='" + getParamValue() + "'" +
            "}";
    }
}
