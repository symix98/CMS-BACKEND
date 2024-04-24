package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.AttachementType;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.Attachement} entity.
 */
public class AttachementDTO implements Serializable {

    private Long id;

    @NotNull
    private String fileName;

    private String description;

    private AttachementType type;

    @Lob
    private byte[] data;

    private String dataContentType;
    private ProjectInfoDTO projectInfo;

    private ReportDTO report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AttachementType getType() {
        return type;
    }

    public void setType(AttachementType type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataContentType() {
        return dataContentType;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    public ProjectInfoDTO getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfoDTO projectInfo) {
        this.projectInfo = projectInfo;
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
        if (!(o instanceof AttachementDTO)) {
            return false;
        }

        AttachementDTO attachementDTO = (AttachementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, attachementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttachementDTO{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", data='" + getData() + "'" +
            ", projectInfo=" + getProjectInfo() +
            ", report=" + getReport() +
            "}";
    }
}
