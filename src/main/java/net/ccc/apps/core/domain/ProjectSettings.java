package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectSettings.
 */
@Entity
@Table(name = "project_settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "property", nullable = false)
    private String property;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private String value;

    @Column(name = "value_type")
    private String valueType;

    @Column(name = "category")
    private String category;

    @Column(name = "is_multiple")
    private Boolean isMultiple;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProjectSettings id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty() {
        return this.property;
    }

    public ProjectSettings property(String property) {
        this.setProperty(property);
        return this;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDescription() {
        return this.description;
    }

    public ProjectSettings description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return this.value;
    }

    public ProjectSettings value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return this.valueType;
    }

    public ProjectSettings valueType(String valueType) {
        this.setValueType(valueType);
        return this;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getCategory() {
        return this.category;
    }

    public ProjectSettings category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIsMultiple() {
        return this.isMultiple;
    }

    public ProjectSettings isMultiple(Boolean isMultiple) {
        this.setIsMultiple(isMultiple);
        return this;
    }

    public void setIsMultiple(Boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectSettings)) {
            return false;
        }
        return id != null && id.equals(((ProjectSettings) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectSettings{" +
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
