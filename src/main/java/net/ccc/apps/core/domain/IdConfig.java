package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * A IdConfig.
 */
@Entity
@Table(name = "id_config")
public class IdConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "postfix")
    private String postfix;

    @Column(name = "counter_start")
    private Integer counterStart;

    @Column(name = "counter_digits")
    private Integer counterDigits;

    @Column(name = "current_counter_value")
    private Integer currentCounterValue;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getName() {
        return this.name;
    }

    public IdConfig name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public IdConfig prefix(String prefix) {
        this.setPrefix(prefix);
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPostfix() {
        return this.postfix;
    }

    public IdConfig postfix(String postfix) {
        this.setPostfix(postfix);
        return this;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public Integer getCounterStart() {
        return this.counterStart;
    }

    public IdConfig counterStart(Integer counterStart) {
        this.setCounterStart(counterStart);
        return this;
    }

    public void setCounterStart(Integer counterStart) {
        this.counterStart = counterStart;
    }

    public Integer getCounterDigits() {
        return this.counterDigits;
    }

    public IdConfig counterDigits(Integer counterDigits) {
        this.setCounterDigits(counterDigits);
        return this;
    }

    public void setCounterDigits(Integer counterDigits) {
        this.counterDigits = counterDigits;
    }

    public Integer getCurrentCounterValue() {
        return this.currentCounterValue;
    }

    public IdConfig currentCounterValue(Integer currentCounterValue) {
        this.setCurrentCounterValue(currentCounterValue);
        return this;
    }

    public void setCurrentCounterValue(Integer currentCounterValue) {
        this.currentCounterValue = currentCounterValue;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdConfig)) {
            return false;
        }
        return name != null && name.equals(((IdConfig) o).name);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IdConfig{" +
            ", name='" + getName() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", postfix='" + getPostfix() + "'" +
            ", counterStart=" + getCounterStart() +
            ", counterDigits=" + getCounterDigits() +
            ", currentCounterValue=" + getCurrentCounterValue() +
            "}";
    }
}
