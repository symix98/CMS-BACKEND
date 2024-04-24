package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Widget.
 */
@Entity
@Table(name = "widget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Widget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "widget_id", nullable = false, unique = true)
    private String widgetId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "category", nullable = false)
    private String category;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Widget id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWidgetId() {
        return this.widgetId;
    }

    public Widget widgetId(String widgetId) {
        this.setWidgetId(widgetId);
        return this;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getName() {
        return this.name;
    }

    public Widget name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public Widget category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Widget)) {
            return false;
        }
        return id != null && id.equals(((Widget) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Widget{" +
            "id=" + getId() +
            ", widgetId='" + getWidgetId() + "'" +
            ", name='" + getName() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
}
