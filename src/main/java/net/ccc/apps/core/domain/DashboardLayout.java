package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DashboardLayout.
 */
@Entity
@Table(name = "dashboard_layout")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardLayout implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "dashboard_layout_id", nullable = false, unique = true)
    private String dashboardLayoutId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "columns", nullable = false)
    private Integer columns;

    @Column(name = "overview")
    private Boolean overview;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DashboardLayout id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDashboardLayoutId() {
        return this.dashboardLayoutId;
    }

    public DashboardLayout dashboardLayoutId(String dashboardLayoutId) {
        this.setDashboardLayoutId(dashboardLayoutId);
        return this;
    }

    public void setDashboardLayoutId(String dashboardLayoutId) {
        this.dashboardLayoutId = dashboardLayoutId;
    }

    public String getName() {
        return this.name;
    }

    public DashboardLayout name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColumns() {
        return this.columns;
    }

    public DashboardLayout columns(Integer columns) {
        this.setColumns(columns);
        return this;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Boolean getOverview() {
        return this.overview;
    }

    public DashboardLayout overview(Boolean overview) {
        this.setOverview(overview);
        return this;
    }

    public void setOverview(Boolean overview) {
        this.overview = overview;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardLayout)) {
            return false;
        }
        return id != null && id.equals(((DashboardLayout) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardLayout{" +
            "id=" + getId() +
            ", dashboardLayoutId='" + getDashboardLayoutId() + "'" +
            ", name='" + getName() + "'" +
            ", columns=" + getColumns() +
            ", overview='" + getOverview() + "'" +
            "}";
    }
}
