package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.Widget} entity.
 */
public class WidgetDTO implements Serializable {

    private Long id;

    @NotNull
    private String widgetId;

    @NotNull
    private String name;

    @NotNull
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof WidgetDTO)) {
            return false;
        }

        WidgetDTO widgetDTO = (WidgetDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, widgetDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WidgetDTO{" +
            "id=" + getId() +
            ", widgetId='" + getWidgetId() + "'" +
            ", name='" + getName() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
}
