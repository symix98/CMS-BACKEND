package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DashboardDetails.
 */
@Entity
@Table(name = "dashboard_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DashboardDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "widget_id", nullable = false)
    private String widgetId;

    @NotNull
    @Column(name = "section", nullable = false)
    private String section;

    @Column(name = "tab")
    private String tab;

    @ManyToOne
    @JsonIgnoreProperties(value = { "dashboardDetails" }, allowSetters = true)
    private Dashboard dashboard;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DashboardDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWidgetId() {
        return this.widgetId;
    }

    public DashboardDetails widgetId(String widgetId) {
        this.setWidgetId(widgetId);
        return this;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getSection() {
        return this.section;
    }

    public DashboardDetails section(String section) {
        this.setSection(section);
        return this;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTab() {
        return this.tab;
    }

    public DashboardDetails tab(String tab) {
        this.setTab(tab);
        return this;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public Dashboard getDashboard() {
        return this.dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public DashboardDetails dashboard(Dashboard dashboard) {
        this.setDashboard(dashboard);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardDetails)) {
            return false;
        }
        return id != null && id.equals(((DashboardDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardDetails{" +
            "id=" + getId() +
            ", widgetId='" + getWidgetId() + "'" +
            ", section='" + getSection() + "'" +
            ", tab='" + getTab() + "'" +
            "}";
    }
}
