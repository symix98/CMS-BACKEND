package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ReportDetails.
 */
@Entity
@Table(name = "report_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReportDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "param_name")
    private String paramName;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "param_type")
    private String paramType;

    @Column(name = "jhi_order")
    private Integer order;

    @Column(name = "mandatory")
    private Boolean mandatory;

    @ManyToOne
    @JsonIgnoreProperties(value = { "attachements", "reportDetails" }, allowSetters = true)
    private Report report;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ReportDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamName() {
        return this.paramName;
    }

    public ReportDetails paramName(String paramName) {
        this.setParamName(paramName);
        return this;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return this.paramType;
    }

    public ReportDetails paramType(String paramType) {
        this.setParamType(paramType);
        return this;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Integer getOrder() {
        return this.order;
    }

    public ReportDetails order(Integer order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public ReportDetails mandatory(Boolean mandatory) {
        this.setMandatory(mandatory);
        return this;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Report getReport() {
        return this.report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ReportDetails report(Report report) {
        this.setReport(report);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public ReportDetails labelName(String labelName) {
        this.setLabelName(labelName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReportDetails)) {
            return false;
        }
        return id != null && id.equals(((ReportDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReportDetails{" +
            "id=" + getId() +
            ", paramName='" + getParamName() + "'" +
            ", labelName='" + getLabelName() + "'" +
            ", paramType='" + getParamType() + "'" +
            ", order=" + getOrder() +
            ", mandatory='" + getMandatory() + "'" +
            "}";
    }
}
