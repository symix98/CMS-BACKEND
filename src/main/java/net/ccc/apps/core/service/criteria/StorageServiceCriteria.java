package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.Filter;
/**
 * Criteria class for the {@link net.ccc.apps.core.domain.StorageService} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.StorageServiceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /storage-services?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class StorageServiceCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter serviceId;

    private StringFilter secret;

    private StringFilter hostname;

    private StringFilter name;

    private BooleanFilter defaultValue;

    public StorageServiceCriteria() {
    }

    public StorageServiceCriteria(StorageServiceCriteria other) {
        this.serviceId = other.serviceId == null ? null : other.serviceId.copy();
        this.secret = other.secret == null ? null : other.secret.copy();
        this.hostname = other.hostname == null ? null : other.hostname.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.defaultValue = other.defaultValue == null ? null : other.defaultValue.copy();
    }

    @Override
    public StorageServiceCriteria copy() {
        return new StorageServiceCriteria(this);
    }

    public StringFilter getServiceId() {
        return serviceId;
    }

    public void setServiceId(StringFilter serviceId) {
        this.serviceId = serviceId;
    }

    public StringFilter getSecret() {
        return secret;
    }

    public void setSecret(StringFilter secret) {
        this.secret = secret;
    }

    public StringFilter getHostname() {
        return hostname;
    }

    public void setHostname(StringFilter hostname) {
        this.hostname = hostname;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public BooleanFilter getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(BooleanFilter defaultValue) {
        this.defaultValue = defaultValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final StorageServiceCriteria that = (StorageServiceCriteria) o;
        return
             Objects.equals(serviceId, that.serviceId) &&
            Objects.equals(secret, that.secret) &&
            Objects.equals(hostname, that.hostname) &&
            Objects.equals(name, that.name) &&
            Objects.equals(defaultValue, that.defaultValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId,
        secret,
        hostname,
        name,
        defaultValue
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StorageServiceCriteria{" +
                (serviceId != null ? "serviceId=" + serviceId + ", " : "") +
                (secret != null ? "secret=" + secret + ", " : "") +
                (hostname != null ? "hostname=" + hostname + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (defaultValue != null ? "defaultValue=" + defaultValue + ", " : "") +
            "}";
    }

}
