package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.ProjectSettings} entity.
 */
public class ProjectSettingsDTO implements Serializable {

    private Long id;

    @NotNull
    private String property;

    private String description;

    private String value;

    private String valueType;

    private String category;

    private Boolean isMultiple;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(Boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectSettingsDTO)) {
            return false;
        }

        ProjectSettingsDTO projectSettingsDTO = (ProjectSettingsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, projectSettingsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectSettingsDTO{" +
            "id=" + getId() +
            ", property='" + getProperty() + "'" +
            ", description='" + getDescription() + "'" +
            ", value='" + getValue() + "'" +
            ", valueType='" + getValueType() + "'" +
            ", category='" + getCategory() + "'" +
            ", isMultiple='" + getIsMultiple() + "'" +
            "}";
    }
}
