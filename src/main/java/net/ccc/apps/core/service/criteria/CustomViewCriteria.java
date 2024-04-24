package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.CustomView} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.CustomViewResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /custom-views?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class CustomViewCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter uiPage;

    private StringFilter filter;

    private StringFilter accessFilter;

    private Boolean distinct;

    public CustomViewCriteria() {}

    public CustomViewCriteria(CustomViewCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.uiPage = other.uiPage == null ? null : other.uiPage.copy();
        this.filter = other.filter == null ? null : other.filter.copy();
        this.accessFilter = other.accessFilter == null ? null : other.accessFilter.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CustomViewCriteria copy() {
        return new CustomViewCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getUiPage() {
        return uiPage;
    }

    public StringFilter uiPage() {
        if (uiPage == null) {
            uiPage = new StringFilter();
        }
        return uiPage;
    }

    public void setUiPage(StringFilter uiPage) {
        this.uiPage = uiPage;
    }

    public StringFilter getFilter() {
        return filter;
    }

    public StringFilter filter() {
        if (filter == null) {
            filter = new StringFilter();
        }
        return filter;
    }

    public void setFilter(StringFilter filter) {
        this.filter = filter;
    }

    public StringFilter getAccessFilter() {
        return accessFilter;
    }

    public StringFilter accessFilter() {
        if (accessFilter == null) {
            accessFilter = new StringFilter();
        }
        return accessFilter;
    }

    public void setAccessFilter(StringFilter accessFilter) {
        this.accessFilter = accessFilter;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CustomViewCriteria that = (CustomViewCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(uiPage, that.uiPage) &&
            Objects.equals(filter, that.filter) &&
            Objects.equals(accessFilter, that.accessFilter) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, uiPage, filter, accessFilter, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomViewCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (uiPage != null ? "uiPage=" + uiPage + ", " : "") +
            (filter != null ? "filter=" + filter + ", " : "") +
            (accessFilter != null ? "accessFilter=" + accessFilter + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
