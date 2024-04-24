package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.DashboardLayout} entity.
 */
public class DashboardLayoutDTO implements Serializable {

    private Long id;

    @NotNull
    private String dashboardLayoutId;

    @NotNull
    private String name;

    @NotNull
    private Integer columns;

    private Boolean overview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDashboardLayoutId() {
        return dashboardLayoutId;
    }

    public void setDashboardLayoutId(String dashboardLayoutId) {
        this.dashboardLayoutId = dashboardLayoutId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Boolean getOverview() {
        return overview;
    }

    public void setOverview(Boolean overview) {
        this.overview = overview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardLayoutDTO)) {
            return false;
        }

        DashboardLayoutDTO dashboardLayoutDTO = (DashboardLayoutDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dashboardLayoutDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardLayoutDTO{" +
            "id=" + getId() +
            ", dashboardLayoutId='" + getDashboardLayoutId() + "'" +
            ", name='" + getName() + "'" +
            ", columns=" + getColumns() +
            ", overview='" + getOverview() + "'" +
            "}";
    }
}
