package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Dashboard.
 */
@Entity
@Table(name = "dashboard")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dashboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dashboard_id")
    private String dashboardId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "dashboard_layout_id", nullable = false)
    private String dashboardLayoutId;

    @OneToMany(mappedBy = "dashboard")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DashboardDetails> dashboardDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getDashboardId() {
        return dashboardId;
    }

    public Dashboard dashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
        return this;
    }

    public void setDashboardId(String dashboardId) {
        this.dashboardId = dashboardId;
    }

    public String getName() {
        return name;
    }

    public Dashboard name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDashboardLayoutId() {
        return dashboardLayoutId;
    }

    public Dashboard dashboardLayoutId(String dashboardLayoutId) {
        this.dashboardLayoutId = dashboardLayoutId;
        return this;
    }

    public void setDashboardLayoutId(String dashboardLayoutId) {
        this.dashboardLayoutId = dashboardLayoutId;
    }

    public Set<DashboardDetails> getDashboardDetails() {
        return dashboardDetails;
    }

    public Dashboard dashboardDetails(Set<DashboardDetails> dashboardDetails) {
        this.dashboardDetails = dashboardDetails;
        return this;
    }

    public Dashboard addDashboardDetails(DashboardDetails dashboardDetails) {
        this.dashboardDetails.add(dashboardDetails);
        dashboardDetails.setDashboard(this);
        return this;
    }

    public Dashboard removeDashboardDetails(DashboardDetails dashboardDetails) {
        this.dashboardDetails.remove(dashboardDetails);
        dashboardDetails.setDashboard(null);
        return this;
    }

    public void setDashboardDetails(Set<DashboardDetails> dashboardDetails) {
        this.dashboardDetails = dashboardDetails;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dashboard)) {
            return false;
        }
        return dashboardId != null && dashboardId.equals(((Dashboard) o).dashboardId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Dashboard{" +
            ", dashboardId='" + getDashboardId() + "'" +
            ", name='" + getName() + "'" +
            ", dashboardLayoutId='" + getDashboardLayoutId() + "'" +
            "}";
    }
}
