package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.CustomView} entity.
 */
public class CustomViewDTO implements Serializable {

    private Long id;

    private String name;

    private String uiPage;

    private String filter;

    private String accessFilter;

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

    public String getUiPage() {
        return uiPage;
    }

    public void setUiPage(String uiPage) {
        this.uiPage = uiPage;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getAccessFilter() {
        return accessFilter;
    }

    public void setAccessFilter(String accessFilter) {
        this.accessFilter = accessFilter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomViewDTO)) {
            return false;
        }

        CustomViewDTO customViewDTO = (CustomViewDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, customViewDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomViewDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", uiPage='" + getUiPage() + "'" +
            ", filter='" + getFilter() + "'" +
            ", accessFilter='" + getAccessFilter() + "'" +
            "}";
    }
}
