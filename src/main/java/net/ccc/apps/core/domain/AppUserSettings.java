package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

import net.ccc.apps.core.domain.AppUser;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AppUserSettings.
 */
@Entity
@Table(name = "app_user_settings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUserSettings implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "property", nullable = false)
    private String property;

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JsonIgnoreProperties(value = { "appUserSettings" }, allowSetters = true)
    private AppUser appUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppUserSettings id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProperty() {
        return this.property;
    }

    public AppUserSettings property(String property) {
        this.setProperty(property);
        return this;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return this.value;
    }

    public AppUserSettings value(String value) {
        this.setValue(value);
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AppUser getAppUser() {
        return this.appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUserSettings appUser(AppUser appUser) {
        this.setAppUser(appUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUserSettings)) {
            return false;
        }
        return id != null && id.equals(((AppUserSettings) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserSettings{" +
            "id=" + getId() +
            ", property='" + getProperty() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
