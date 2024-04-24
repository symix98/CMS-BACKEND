package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.Dashboard} entity.
 */
public class DashboardDTO implements Serializable {

    @NotNull
    private String dashboardId;

    @NotNull
    private String name;

    @NotNull
    private String dashboardLayoutId;

    public String getDashboardId() {
        return dashboardId;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDashboardLayoutId() {
        return dashboardLayoutId;
    }

    public void setDashboardLayoutId(String dashboardLayoutId) {
        this.dashboardLayoutId = dashboardLayoutId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardDTO)) {
            return false;
        }

        DashboardDTO dashboardDTO = (DashboardDTO) o;
        if (this.dashboardId == null) {
            return false;
        }
        return Objects.equals(this.dashboardId, dashboardDTO.dashboardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.dashboardId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardDTO{" +
            ", dashboardId='" + getDashboardId() + "'" +
            ", name='" + getName() + "'" +
            ", dashboardLayoutId='" + getDashboardLayoutId() + "'" +
            "}";
    }
}
