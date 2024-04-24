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
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.ProjectInfo} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.ProjectInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /project-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ProjectInfoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter projectId;

    private StringFilter contractNumber;

    private StringFilter name;

    private LocalDateFilter startDate;

    private LocalDateFilter endDate;

    private LongFilter attachementId;

    private Boolean distinct;

    public ProjectInfoCriteria() {}

    public ProjectInfoCriteria(ProjectInfoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.projectId = other.projectId == null ? null : other.projectId.copy();
        this.contractNumber = other.contractNumber == null ? null : other.contractNumber.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.startDate = other.startDate == null ? null : other.startDate.copy();
        this.endDate = other.endDate == null ? null : other.endDate.copy();
        this.attachementId = other.attachementId == null ? null : other.attachementId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProjectInfoCriteria copy() {
        return new ProjectInfoCriteria(this);
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

    public StringFilter getProjectId() {
        return projectId;
    }

    public StringFilter projectId() {
        if (projectId == null) {
            projectId = new StringFilter();
        }
        return projectId;
    }

    public void setProjectId(StringFilter projectId) {
        this.projectId = projectId;
    }

    public StringFilter getContractNumber() {
        return contractNumber;
    }

    public StringFilter contractNumber() {
        if (contractNumber == null) {
            contractNumber = new StringFilter();
        }
        return contractNumber;
    }

    public void setContractNumber(StringFilter contractNumber) {
        this.contractNumber = contractNumber;
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

    public LocalDateFilter getStartDate() {
        return startDate;
    }

    public LocalDateFilter startDate() {
        if (startDate == null) {
            startDate = new LocalDateFilter();
        }
        return startDate;
    }

    public void setStartDate(LocalDateFilter startDate) {
        this.startDate = startDate;
    }

    public LocalDateFilter getEndDate() {
        return endDate;
    }

    public LocalDateFilter endDate() {
        if (endDate == null) {
            endDate = new LocalDateFilter();
        }
        return endDate;
    }

    public void setEndDate(LocalDateFilter endDate) {
        this.endDate = endDate;
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
        final ProjectInfoCriteria that = (ProjectInfoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(projectId, that.projectId) &&
            Objects.equals(contractNumber, that.contractNumber) &&
            Objects.equals(name, that.name) &&
            Objects.equals(startDate, that.startDate) &&
            Objects.equals(endDate, that.endDate) &&
            Objects.equals(attachementId, that.attachementId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, contractNumber, name, startDate, endDate, attachementId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (projectId != null ? "projectId=" + projectId + ", " : "") +
            (contractNumber != null ? "contractNumber=" + contractNumber + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (startDate != null ? "startDate=" + startDate + ", " : "") +
            (endDate != null ? "endDate=" + endDate + ", " : "") +
            (attachementId != null ? "attachementId=" + attachementId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
