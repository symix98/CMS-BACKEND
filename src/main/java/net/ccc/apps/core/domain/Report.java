package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "report_path")
    private String reportPath;

    @Column(name = "description")
    private String description;


    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "report")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "projectInfo", "report" }, allowSetters = true)
    private Set<Attachement> attachements = new HashSet<>();

    @OneToMany(mappedBy = "report")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "report" }, allowSetters = true)
    private Set<ReportDetails> reportDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Report id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Report name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReportPath() {
        return this.reportPath;
    }

    public Report reportPath(String reportPath) {
        this.setReportPath(reportPath);
        return this;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getDescription() {
        return this.description;
    }

    public Report description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Attachement> getAttachements() {
        return this.attachements;
    }

    public void setAttachements(Set<Attachement> attachements) {
        if (this.attachements != null) {
            this.attachements.forEach(i -> i.setReport(null));
        }
        if (attachements != null) {
            attachements.forEach(i -> i.setReport(this));
        }
        this.attachements = attachements;
    }

    public Report attachements(Set<Attachement> attachements) {
        this.setAttachements(attachements);
        return this;
    }

    public Report addAttachement(Attachement attachement) {
        this.attachements.add(attachement);
        attachement.setReport(this);
        return this;
    }

    public Report removeAttachement(Attachement attachement) {
        this.attachements.remove(attachement);
        attachement.setReport(null);
        return this;
    }

    public Set<ReportDetails> getReportDetails() {
        return this.reportDetails;
    }

    public void setReportDetails(Set<ReportDetails> reportDetails) {
        if (this.reportDetails != null) {
            this.reportDetails.forEach(i -> i.setReport(null));
        }
        if (reportDetails != null) {
            reportDetails.forEach(i -> i.setReport(this));
        }
        this.reportDetails = reportDetails;
    }

    public Report reportDetails(Set<ReportDetails> reportDetails) {
        this.setReportDetails(reportDetails);
        return this;
    }

    public Report addReportDetails(ReportDetails reportDetails) {
        this.reportDetails.add(reportDetails);
        reportDetails.setReport(this);
        return this;
    }

    public Report removeReportDetails(ReportDetails reportDetails) {
        this.reportDetails.remove(reportDetails);
        reportDetails.setReport(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report)) {
            return false;
        }
        return id != null && id.equals(((Report) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Report{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", category='" + getCategory() + "'" +
            ", reportPath='" + getReportPath() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
