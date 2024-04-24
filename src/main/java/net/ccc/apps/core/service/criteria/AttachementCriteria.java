package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import net.ccc.apps.core.domain.enumeration.AttachementType;
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
 * Criteria class for the {@link net.ccc.apps.core.domain.Attachement} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.AttachementResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /attachements?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class AttachementCriteria implements Serializable, Criteria {

    /**
     * Class for filtering AttachementType
     */
    public static class AttachementTypeFilter extends Filter<AttachementType> {

        public AttachementTypeFilter() {}

        public AttachementTypeFilter(AttachementTypeFilter filter) {
            super(filter);
        }

        @Override
        public AttachementTypeFilter copy() {
            return new AttachementTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fileName;

    private StringFilter description;

    private AttachementTypeFilter type;

    private LongFilter projectInfoId;

    private LongFilter reportId;

    private Boolean distinct;

    public AttachementCriteria() {}

    public AttachementCriteria(AttachementCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fileName = other.fileName == null ? null : other.fileName.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.projectInfoId = other.projectInfoId == null ? null : other.projectInfoId.copy();
        this.reportId = other.reportId == null ? null : other.reportId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AttachementCriteria copy() {
        return new AttachementCriteria(this);
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

    public StringFilter getFileName() {
        return fileName;
    }

    public StringFilter fileName() {
        if (fileName == null) {
            fileName = new StringFilter();
        }
        return fileName;
    }

    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
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

    public AttachementTypeFilter getType() {
        return type;
    }

    public AttachementTypeFilter type() {
        if (type == null) {
            type = new AttachementTypeFilter();
        }
        return type;
    }

    public void setType(AttachementTypeFilter type) {
        this.type = type;
    }

    public LongFilter getProjectInfoId() {
        return projectInfoId;
    }

    public LongFilter projectInfoId() {
        if (projectInfoId == null) {
            projectInfoId = new LongFilter();
        }
        return projectInfoId;
    }

    public void setProjectInfoId(LongFilter projectInfoId) {
        this.projectInfoId = projectInfoId;
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
        final AttachementCriteria that = (AttachementCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(fileName, that.fileName) &&
            Objects.equals(description, that.description) &&
            Objects.equals(type, that.type) &&
            Objects.equals(projectInfoId, that.projectInfoId) &&
            Objects.equals(reportId, that.reportId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, description, type, projectInfoId, reportId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttachementCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (fileName != null ? "fileName=" + fileName + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (projectInfoId != null ? "projectInfoId=" + projectInfoId + ", " : "") +
            (reportId != null ? "reportId=" + reportId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
