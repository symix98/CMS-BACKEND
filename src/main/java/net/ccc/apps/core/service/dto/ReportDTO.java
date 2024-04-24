package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.Report} entity.
 */
public class ReportDTO implements Serializable {

    private Long id;

    private String name;

    private String reportPath;

    private String description;
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportDTO)) {
            return false;
        }

        ReportDTO reportDTO = (ReportDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reportDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", reportPath='" + getReportPath() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
