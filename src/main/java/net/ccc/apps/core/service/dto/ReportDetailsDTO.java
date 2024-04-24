package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.ReportDetails} entity.
 */
public class ReportDetailsDTO implements Serializable {

    private Long id;

    private String paramName;

    private String paramType;

    private Integer order;

    private Boolean mandatory;

    private ReportDTO report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public ReportDTO getReport() {
        return report;
    }

    public void setReport(ReportDTO report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportDetailsDTO)) {
            return false;
        }

        ReportDetailsDTO reportDetailsDTO = (ReportDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reportDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDetailsDTO{" +
            "id=" + getId() +
            ", paramName='" + getParamName() + "'" +
            ", paramType='" + getParamType() + "'" +
            ", order=" + getOrder() +
            ", mandatory='" + getMandatory() + "'" +
            ", report=" + getReport() +
            "}";
    }
}
