package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.DataType} entity.
 */
public class DataTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private Boolean complex;

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

    public Boolean getComplex() {
        return complex;
    }

    public void setComplex(Boolean complex) {
        this.complex = complex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataTypeDTO)) {
            return false;
        }

        DataTypeDTO dataTypeDTO = (DataTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dataTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", complex='" + getComplex() + "'" +
            "}";
    }
}
