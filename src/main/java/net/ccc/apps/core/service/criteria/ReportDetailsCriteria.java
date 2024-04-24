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
 * Criteria class for the {@link net.ccc.apps.core.domain.ReportDetails} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.ReportDetailsResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /report-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ReportDetailsCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter paramName;

    private StringFilter paramType;

    private IntegerFilter order;

    private BooleanFilter mandatory;

    private LongFilter reportId;

    private Boolean distinct;

    public ReportDetailsCriteria() {}

    public ReportDetailsCriteria(ReportDetailsCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.paramName = other.paramName == null ? null : other.paramName.copy();
        this.paramType = other.paramType == null ? null : other.paramType.copy();
        this.order = other.order == null ? null : other.order.copy();
        this.mandatory = other.mandatory == null ? null : other.mandatory.copy();
        this.reportId = other.reportId == null ? null : other.reportId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ReportDetailsCriteria copy() {
        return new ReportDetailsCriteria(this);
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

    public StringFilter getParamName() {
        return paramName;
    }

    public StringFilter paramName() {
        if (paramName == null) {
            paramName = new StringFilter();
        }
        return paramName;
    }

    public void setParamName(StringFilter paramName) {
        this.paramName = paramName;
    }

    public StringFilter getParamType() {
        return paramType;
    }

    public StringFilter paramType() {
        if (paramType == null) {
            paramType = new StringFilter();
        }
        return paramType;
    }

    public void setParamType(StringFilter paramType) {
        this.paramType = paramType;
    }

    public IntegerFilter getOrder() {
        return order;
    }

    public IntegerFilter order() {
        if (order == null) {
            order = new IntegerFilter();
        }
        return order;
    }

    public void setOrder(IntegerFilter order) {
        this.order = order;
    }

    public BooleanFilter getMandatory() {
        return mandatory;
    }

    public BooleanFilter mandatory() {
        if (mandatory == null) {
            mandatory = new BooleanFilter();
        }
        return mandatory;
    }

    public void setMandatory(BooleanFilter mandatory) {
        this.mandatory = mandatory;
    }

    public LongFilter getReportId() {
        return reportId;
    }

    public LongFilter reportId() {
        if (reportId == null) {
            reportId = new LongFilter();
        }
        return reportId;
    }

    public void setReportId(LongFilter reportId) {
        this.reportId = reportId;
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
        final ReportDetailsCriteria that = (ReportDetailsCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(paramName, that.paramName) &&
            Objects.equals(paramType, that.paramType) &&
            Objects.equals(order, that.order) &&
            Objects.equals(mandatory, that.mandatory) &&
            Objects.equals(reportId, that.reportId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paramName, paramType, order, mandatory, reportId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDetailsCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (paramName != null ? "paramName=" + paramName + ", " : "") +
            (paramType != null ? "paramType=" + paramType + ", " : "") +
            (order != null ? "order=" + order + ", " : "") +
            (mandatory != null ? "mandatory=" + mandatory + ", " : "") +
            (reportId != null ? "reportId=" + reportId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
