package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AppUser.
 */
@Entity
@Table(name = "app_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "is_administrator")
    private Boolean isAdministrator;

    @Column(name = "inactive")
    private Boolean inactive;

    @OneToMany(mappedBy = "appUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "appUser" }, allowSetters = true)
    private Set<AppUserSettings> appUserSettings = new HashSet<>();

    // prettier-ignore
    @ManyToMany(fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "app_user_role",
        joinColumns = @JoinColumn(name = "appuser_user_id", referencedColumnName = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_role_id", referencedColumnName = "role_id"))
    // prettier-on

    private Set<Role> roles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    @Column(name = "attachment_id")
    private Long attachmentId;

    @OneToOne
    @JoinColumns({ @JoinColumn(name = "attachment_id", insertable = false, updatable = false) })
    private Attachement attachement;

    @OneToMany(mappedBy = "initiatedBy")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "appUser" }, allowSetters = true)
    private Set<WorkflowProcess> workflowProcesses = new HashSet<>();

    public Attachement getAttachement() {
        return attachement;
    }

    public AppUser attachement(Attachement attachement) {
        this.attachement = attachement;
        return this;
    }

    public void setAttachement(Attachement attachement) {
        this.attachement = attachement;
    }

    public String getUserId() {
        return this.userId;
    }

    public AppUser userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public AppUser name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public AppUser email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsAdministrator() {
        return this.isAdministrator;
    }

    public AppUser isAdministrator(Boolean isAdministrator) {
        this.setIsAdministrator(isAdministrator);
        return this;
    }

    public void setIsAdministrator(Boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public Boolean getInactive() {
        return this.inactive;
    }

    public AppUser inactive(Boolean inactive) {
        this.setInactive(inactive);
        return this;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Set<AppUserSettings> getAppUserSettings() {
        return this.appUserSettings;
    }

    public void setAppUserSettings(Set<AppUserSettings> appUserSettings) {
        if (this.appUserSettings != null) {
            this.appUserSettings.forEach(i -> i.setAppUser(null));
        }
        if (appUserSettings != null) {
            appUserSettings.forEach(i -> i.setAppUser(this));
        }
        this.appUserSettings = appUserSettings;
    }

    public AppUser appUserSettings(Set<AppUserSettings> appUserSettings) {
        this.setAppUserSettings(appUserSettings);
        return this;
    }

    public AppUser addAppUserSettings(AppUserSettings appUserSettings) {
        this.appUserSettings.add(appUserSettings);
        appUserSettings.setAppUser(this);
        return this;
    }

    public AppUser removeAppUserSettings(AppUserSettings appUserSettings) {
        this.appUserSettings.remove(appUserSettings);
        appUserSettings.setAppUser(null);
        return this;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public AppUser roles(Set<Role> roles) {
        this.setRoles(roles);
        return this;
    }

    public AppUser addRole(Role role) {
        this.roles.add(role);
        role.getAppUsers().add(this);
        return this;
    }

    public AppUser removeRole(Role role) {
        this.roles.remove(role);
        role.getAppUsers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Set<WorkflowProcess> getWorkflowProcesses() {
        return workflowProcesses;
    }

    public AppUser workflowProcesses(Set<WorkflowProcess> workflowProcesses) {
        this.workflowProcesses = workflowProcesses;
        return this;
    }

    public AppUser addWorkflowProcess(WorkflowProcess workflowProcess) {
        this.workflowProcesses.add(workflowProcess);
        workflowProcess.setInitiatedBy(this);
        return this;
    }

    public AppUser removeWorkflowProcess(WorkflowProcess workflowProcess) {
        this.workflowProcesses.remove(workflowProcess);
        workflowProcess.setInitiatedBy(null);
        return this;
    }

    public void setWorkflowProcesses(Set<WorkflowProcess> workflowProcesses) {
        this.workflowProcesses = workflowProcesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUser)) {
            return false;
        }
        return userId != null && userId.equals(((AppUser) o).userId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUser{" +
            "userId='" + userId + '\'' +
            ", userId='" + getUserId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", isAdministrator='" + getIsAdministrator() + "'" +
            ", inactive='" + getInactive() + "'" +
            ", attachmentId=" + attachmentId +
            ", attachement=" + attachement +
            "}";
    }
}
