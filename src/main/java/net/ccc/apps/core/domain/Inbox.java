package net.ccc.apps.core.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.FormType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Inbox.
 */
@Entity
@Table(name = "inbox")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Inbox implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_time")
    private Instant dateTime;

    @Column(name = "form_id")
    private Long formId;

    @Enumerated(EnumType.STRING)
    @Column(name = "form_type")
    private FormType formType;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

    @Column(name = "unread")
    private Boolean unread;

    @Column(name = "read_time")
    private Instant readTime;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(unique = true)
    private AppUser assignedTo;

    @OneToOne
    @JoinColumn(unique = true)
    private AppUser assignedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public Inbox dateTime(Instant dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Long getFormId() {
        return formId;
    }

    public Inbox formId(Long formId) {
        this.formId = formId;
        return this;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public FormType getFormType() {
        return formType;
    }

    public Inbox formType(FormType formType) {
        this.formType = formType;
        return this;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public String getTitle() {
        return title;
    }

    public Inbox title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public Inbox message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isUnread() {
        return unread;
    }

    public Inbox unread(Boolean unread) {
        this.unread = unread;
        return this;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

    public Instant getReadTime() {
        return readTime;
    }

    public Inbox readTime(Instant readTime) {
        this.readTime = readTime;
        return this;
    }

    public void setReadTime(Instant readTime) {
        this.readTime = readTime;
    }

    public String getDescription() {
        return description;
    }

    public Inbox description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppUser getAssignedTo() {
        return assignedTo;
    }

    public Inbox assignedTo(AppUser appUser) {
        this.assignedTo = appUser;
        return this;
    }

    public void setAssignedTo(AppUser appUser) {
        this.assignedTo = appUser;
    }

    public AppUser getAssignedBy() {
        return assignedBy;
    }

    public Inbox assignedBy(AppUser appUser) {
        this.assignedBy = appUser;
        return this;
    }

    public void setAssignedBy(AppUser appUser) {
        this.assignedBy = appUser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Inbox)) {
            return false;
        }
        return id != null && id.equals(((Inbox) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Inbox{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", formId=" + getFormId() +
            ", formType='" + getFormType() + "'" +
            ", title='" + getTitle() + "'" +
            ", message='" + getMessage() + "'" +
            ", unread='" + isUnread() + "'" +
            ", readTime='" + getReadTime() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
