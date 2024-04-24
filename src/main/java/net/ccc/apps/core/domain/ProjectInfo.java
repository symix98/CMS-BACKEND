package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectInfo.
 */
@Entity
@Table(name = "project_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(name = "contract_number")
    private String contractNumber;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "projectInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "projectInfo", "report" }, allowSetters = true)
    private Set<Attachement> attachements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProjectInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectId() {
        return this.projectId;
    }

    public ProjectInfo projectId(String projectId) {
        this.setProjectId(projectId);
        return this;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public ProjectInfo contractNumber(String contractNumber) {
        this.setContractNumber(contractNumber);
        return this;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getName() {
        return this.name;
    }

    public ProjectInfo name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public ProjectInfo startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public ProjectInfo endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Set<Attachement> getAttachements() {
        return this.attachements;
    }

    public void setAttachements(Set<Attachement> attachements) {
        if (this.attachements != null) {
            this.attachements.forEach(i -> i.setProjectInfo(null));
        }
        if (attachements != null) {
            attachements.forEach(i -> i.setProjectInfo(this));
        }
        this.attachements = attachements;
    }

    public ProjectInfo attachements(Set<Attachement> attachements) {
        this.setAttachements(attachements);
        return this;
    }

    public ProjectInfo addAttachement(Attachement attachement) {
        this.attachements.add(attachement);
        attachement.setProjectInfo(this);
        return this;
    }

    public ProjectInfo removeAttachement(Attachement attachement) {
        this.attachements.remove(attachement);
        attachement.setProjectInfo(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectInfo)) {
            return false;
        }
        return id != null && id.equals(((ProjectInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectInfo{" +
            "id=" + getId() +
            ", projectId='" + getProjectId() + "'" +
            ", contractNumber='" + getContractNumber() + "'" +
            ", name='" + getName() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
