package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.IdConfig} entity.
 */
public class IdConfigDTO implements Serializable {

    @NotNull
    private String name;

    private String prefix;

    private String postfix;

    private Integer counterStart;

    private Integer counterDigits;

    private Integer currentCounterValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public Integer getCounterStart() {
        return counterStart;
    }

    public void setCounterStart(Integer counterStart) {
        this.counterStart = counterStart;
    }

    public Integer getCounterDigits() {
        return counterDigits;
    }

    public void setCounterDigits(Integer counterDigits) {
        this.counterDigits = counterDigits;
    }

    public Integer getCurrentCounterValue() {
        return currentCounterValue;
    }

    public void setCurrentCounterValue(Integer currentCounterValue) {
        this.currentCounterValue = currentCounterValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IdConfigDTO)) {
            return false;
        }

        IdConfigDTO idConfigDTO = (IdConfigDTO) o;
        if (this.name == null) {
            return false;
        }
        return Objects.equals(this.name, idConfigDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IdConfigDTO{" +
            ", name='" + getName() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", postfix='" + getPostfix() + "'" +
            ", counterStart=" + getCounterStart() +
            ", counterDigits=" + getCounterDigits() +
            ", currentCounterValue=" + getCurrentCounterValue() +
            "}";
    }
}
