package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CustomView.
 */
@Entity
@Table(name = "custom_view")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ui_page")
    private String uiPage;

    @Column(name = "filter")
    private String filter;

    @Column(name = "access_filter")
    private String accessFilter;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CustomView id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public CustomView name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUiPage() {
        return this.uiPage;
    }

    public CustomView uiPage(String uiPage) {
        this.setUiPage(uiPage);
        return this;
    }

    public void setUiPage(String uiPage) {
        this.uiPage = uiPage;
    }

    public String getFilter() {
        return this.filter;
    }

    public CustomView filter(String filter) {
        this.setFilter(filter);
        return this;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getAccessFilter() {
        return this.accessFilter;
    }

    public CustomView accessFilter(String accessFilter) {
        this.setAccessFilter(accessFilter);
        return this;
    }

    public void setAccessFilter(String accessFilter) {
        this.accessFilter = accessFilter;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomView)) {
            return false;
        }
        return id != null && id.equals(((CustomView) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomView{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", uiPage='" + getUiPage() + "'" +
            ", filter='" + getFilter() + "'" +
            ", accessFilter='" + getAccessFilter() + "'" +
            "}";
    }
}
