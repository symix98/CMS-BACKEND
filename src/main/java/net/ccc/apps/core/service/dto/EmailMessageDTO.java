package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.ccc.app.lms.domain.EmailMessage} entity.
 */
public class EmailMessageDTO implements Serializable {

    private Long id;

    private String from;

    private String subject;

    private String content;

    private String referenceId;

    private Boolean isSent;

    private Instant sentTime;

    private String comment;

    private Long attemptsNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

    public Instant getSentTime() {
        return sentTime;
    }

    public void setSentTime(Instant sentTime) {
        this.sentTime = sentTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getAttemptsNo() {
        return attemptsNo;
    }

    public void setAttemptsNo(Long attemptsNo) {
        this.attemptsNo = attemptsNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailMessageDTO)) {
            return false;
        }

        EmailMessageDTO emailMessageDTO = (EmailMessageDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, emailMessageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "EmailMessageDTO{" +
            "id=" + id +
            ", from='" + from + '\'' +
            ", subject='" + subject + '\'' +
            ", content='" + content + '\'' +
            ", referenceId='" + referenceId + '\'' +
            ", isSent=" + isSent +
            ", sentTime=" + sentTime +
            ", comment='" + comment + '\'' +
            ", attemptsNo=" + attemptsNo +
            '}';
    }
}
