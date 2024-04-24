package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.DashboardDetails} entity.
 */
public class DashboardDetailsDTO implements Serializable {

    private Long id;

    @NotNull
    private String widgetId;

    @NotNull
    private String section;

    private String tab;

    @NotNull
    private String dashboardId;

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardDetailsDTO)) {
            return false;
        }

        DashboardDetailsDTO dashboardDetailsDTO = (DashboardDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dashboardDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardDetailsDTO{" +
            "id=" + getId() +
            ", widgetId='" + getWidgetId() + "'" +
            ", section='" + getSection() + "'" +
            ", tab='" + getTab() + "'" +
            ", dashboardId=" + getDashboardId() +
            "}";
    }
}
