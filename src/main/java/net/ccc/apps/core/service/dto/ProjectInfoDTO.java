package net.ccc.apps.core.service.dto;

import net.ccc.apps.core.domain.Attachement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.ProjectInfo} entity.
 */
public class ProjectInfoDTO implements Serializable {

    private Long id;

    @NotNull
    private String projectId;

    private String contractNumber;

    @NotNull
    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Attachement> attachements;

    public List<Attachement> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<Attachement> attachements) {
        this.attachements = attachements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectInfoDTO)) {
            return false;
        }

        ProjectInfoDTO projectInfoDTO = (ProjectInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, projectInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectInfoDTO{" +
            "id=" + getId() +
            ", projectId='" + getProjectId() + "'" +
            ", contractNumber='" + getContractNumber() + "'" +
            ", name='" + getName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
