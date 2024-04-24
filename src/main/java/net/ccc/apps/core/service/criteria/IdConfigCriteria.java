package net.ccc.apps.core.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.IdConfig} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /id-configs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class IdConfigCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter name;

    private StringFilter prefix;

    private StringFilter postfix;

    private IntegerFilter counterStart;

    private IntegerFilter counterDigits;

    private IntegerFilter currentCounterValue;

    private Boolean distinct;

    public IdConfigCriteria() {}

    public IdConfigCriteria(IdConfigCriteria other) {
        this.name = other.name == null ? null : other.name.copy();
        this.prefix = other.prefix == null ? null : other.prefix.copy();
        this.postfix = other.postfix == null ? null : other.postfix.copy();
        this.counterStart = other.counterStart == null ? null : other.counterStart.copy();
        this.counterDigits = other.counterDigits == null ? null : other.counterDigits.copy();
        this.currentCounterValue = other.currentCounterValue == null ? null : other.currentCounterValue.copy();
        this.distinct = other.distinct;
    }

    @Override
    public IdConfigCriteria copy() {
        return new IdConfigCriteria(this);
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getPrefix() {
        return prefix;
    }

    public StringFilter prefix() {
        if (prefix == null) {
            prefix = new StringFilter();
        }
        return prefix;
    }

    public void setPrefix(StringFilter prefix) {
        this.prefix = prefix;
    }

    public StringFilter getPostfix() {
        return postfix;
    }

    public StringFilter postfix() {
        if (postfix == null) {
            postfix = new StringFilter();
        }
        return postfix;
    }

    public void setPostfix(StringFilter postfix) {
        this.postfix = postfix;
    }

    public IntegerFilter getCounterStart() {
        return counterStart;
    }

    public IntegerFilter counterStart() {
        if (counterStart == null) {
            counterStart = new IntegerFilter();
        }
        return counterStart;
    }

    public void setCounterStart(IntegerFilter counterStart) {
        this.counterStart = counterStart;
    }

    public IntegerFilter getCounterDigits() {
        return counterDigits;
    }

    public IntegerFilter counterDigits() {
        if (counterDigits == null) {
            counterDigits = new IntegerFilter();
        }
        return counterDigits;
    }

    public void setCounterDigits(IntegerFilter counterDigits) {
        this.counterDigits = counterDigits;
    }

    public IntegerFilter getCurrentCounterValue() {
        return currentCounterValue;
    }

    public IntegerFilter currentCounterValue() {
        if (currentCounterValue == null) {
            currentCounterValue = new IntegerFilter();
        }
        return currentCounterValue;
    }

    public void setCurrentCounterValue(IntegerFilter currentCounterValue) {
        this.currentCounterValue = currentCounterValue;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final IdConfigCriteria that = (IdConfigCriteria) o;
        return (
            Objects.equals(name, that.name) &&
            Objects.equals(prefix, that.prefix) &&
            Objects.equals(postfix, that.postfix) &&
            Objects.equals(counterStart, that.counterStart) &&
            Objects.equals(counterDigits, that.counterDigits) &&
            Objects.equals(currentCounterValue, that.currentCounterValue) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prefix, postfix, counterStart, counterDigits, currentCounterValue, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IdConfigCriteria{" +
            (name != null ? "name=" + name + ", " : "") +
            (prefix != null ? "prefix=" + prefix + ", " : "") +
            (postfix != null ? "postfix=" + postfix + ", " : "") +
            (counterStart != null ? "counterStart=" + counterStart + ", " : "") +
            (counterDigits != null ? "counterDigits=" + counterDigits + ", " : "") +
            (currentCounterValue != null ? "currentCounterValue=" + currentCounterValue + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
