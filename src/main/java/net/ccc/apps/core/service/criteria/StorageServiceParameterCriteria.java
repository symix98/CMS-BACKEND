package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.Filter;
/**
 * Criteria class for the {@link net.ccc.apps.core.domain.StorageServiceParameter} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.StorageServiceParameterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /storage-service-parameters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StorageServiceParameterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter serviceId;

    private StringFilter paramName;

    private StringFilter paramValue;

    public StorageServiceParameterCriteria() {
    }

    public StorageServiceParameterCriteria(StorageServiceParameterCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.serviceId = other.serviceId == null ? null : other.serviceId.copy();
        this.paramName = other.paramName == null ? null : other.paramName.copy();
        this.paramValue = other.paramValue == null ? null : other.paramValue.copy();
    }

    @Override
    public StorageServiceParameterCriteria copy() {
        return new StorageServiceParameterCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getServiceId() {
        return serviceId;
    }

    public void setServiceId(StringFilter serviceId) {
        this.serviceId = serviceId;
    }

    public StringFilter getParamName() {
        return paramName;
    }

    public void setParamName(StringFilter paramName) {
        this.paramName = paramName;
    }

    public StringFilter getParamValue() {
        return paramValue;
    }

    public void setParamValue(StringFilter paramValue) {
        this.paramValue = paramValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StorageServiceParameterCriteria that = (StorageServiceParameterCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(serviceId, that.serviceId) &&
            Objects.equals(paramName, that.paramName) &&
            Objects.equals(paramValue, that.paramValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        serviceId,
        paramName,
        paramValue
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageServiceParameterCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (serviceId != null ? "serviceId=" + serviceId + ", " : "") +
                (paramName != null ? "paramName=" + paramName + ", " : "") +
                (paramValue != null ? "paramValue=" + paramValue + ", " : "") +
            "}";
    }

}
