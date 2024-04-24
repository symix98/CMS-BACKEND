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
 * Criteria class for the {@link net.ccc.apps.core.domain.Report} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.ReportResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /reports?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ReportCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter reportPath;

    private StringFilter description;

    private LongFilter attachementId;

    private LongFilter reportDetailsId;

    private Boolean distinct;

    public ReportCriteria() {}

    public ReportCriteria(ReportCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.reportPath = other.reportPath == null ? null : other.reportPath.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.attachementId = other.attachementId == null ? null : other.attachementId.copy();
        this.reportDetailsId = other.reportDetailsId == null ? null : other.reportDetailsId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ReportCriteria copy() {
        return new ReportCriteria(this);
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

    public StringFilter getReportPath() {
        return reportPath;
    }

    public StringFilter reportPath() {
        if (reportPath == null) {
            reportPath = new StringFilter();
        }
        return reportPath;
    }

    public void setReportPath(StringFilter reportPath) {
        this.reportPath = reportPath;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public LongFilter getAttachementId() {
        return attachementId;
    }

    public LongFilter attachementId() {
        if (attachementId == null) {
            attachementId = new LongFilter();
        }
        return attachementId;
    }

    public void setAttachementId(LongFilter attachementId) {
        this.attachementId = attachementId;
    }

    public LongFilter getReportDetailsId() {
        return reportDetailsId;
    }

    public LongFilter reportDetailsId() {
        if (reportDetailsId == null) {
            reportDetailsId = new LongFilter();
        }
        return reportDetailsId;
    }

    public void setReportDetailsId(LongFilter reportDetailsId) {
        this.reportDetailsId = reportDetailsId;
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
        final ReportCriteria that = (ReportCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(reportPath, that.reportPath) &&
            Objects.equals(description, that.description) &&
            Objects.equals(attachementId, that.attachementId) &&
            Objects.equals(reportDetailsId, that.reportDetailsId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reportPath, description, attachementId, reportDetailsId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (reportPath != null ? "reportPath=" + reportPath + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (attachementId != null ? "attachementId=" + attachementId + ", " : "") +
            (reportDetailsId != null ? "reportDetailsId=" + reportDetailsId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
