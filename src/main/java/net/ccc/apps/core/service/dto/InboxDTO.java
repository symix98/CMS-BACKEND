package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;
import net.ccc.apps.core.domain.enumeration.FormType;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.Inbox} entity.
 */
public class InboxDTO implements Serializable {

    private Long id;

    private Instant dateTime;

    private Long formId;

    private FormType formType;

    private String title;

    private String message;

    private Boolean unread;

    private Instant readTime;

    private String description;

    private String assignedToId;

    private String assignedToName;

    private String assignedById;

    private String assignedByName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isUnread() {
        return unread;
    }

    public void setUnread(Boolean unread) {
        this.unread = unread;
    }

    public Instant getReadTime() {
        return readTime;
    }

    public void setReadTime(Instant readTime) {
        this.readTime = readTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(String tUserId) {
        this.assignedToId = tUserId;
    }

    public String getAssignedToName() {
        return assignedToName;
    }

    public void setAssignedToName(String assignedToName) {
        this.assignedToName = assignedToName;
    }

    public String getAssignedById() {
        return assignedById;
    }

    public void setAssignedById(String tUserId) {
        this.assignedById = tUserId;
    }

    public String getAssignedByName() {
        return assignedByName;
    }

    public void setAssignedByName(String assignedByName) {
        this.assignedByName = assignedByName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InboxDTO)) {
            return false;
        }

        return id != null && id.equals(((InboxDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InboxDTO{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", formId=" + getFormId() +
            ", formType='" + getFormType() + "'" +
            ", title='" + getTitle() + "'" +
            ", message='" + getMessage() + "'" +
            ", unread='" + isUnread() + "'" +
            ", readTime='" + getReadTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", assignedToId=" + getAssignedToId() +
            ", assignedById=" + getAssignedById() +
            ", assignedToName=" + getAssignedToName() +
            ", assignedByName=" + getAssignedByName() +
            "}";
    }
}
