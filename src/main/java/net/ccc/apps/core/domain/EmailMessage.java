package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A EmailMessage.
 */
@Entity
@Table(name = "email_message")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmailMessage extends AbstractAuditingEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_message_gen")
    @SequenceGenerator(name = "email_message_gen",allocationSize = 1)
    private Long id;

    @Column(name = "jhi_from")
    private String from;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "is_sent")
    private Boolean isSent;

    @Column(name = "sent_time")
    private Instant sentTime;

    @Column(name = "comment")
    private String comment;

    @Column(name = "attempts_no")
    Long attemptsNo;

    @OneToMany(mappedBy = "recipients",fetch =FetchType.EAGER ,cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "recipients" }, allowSetters = true)
    private Set<MailRecipient> recipients = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmailMessage id(Long id) {
        this.id = id;
        return this;
    }

    public String getFrom() {
        return this.from;
    }

    public EmailMessage from(String from) {
        this.from = from;
        return this;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return this.subject;
    }

    public EmailMessage subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public EmailMessage content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReferenceId() {
        return this.referenceId;
    }

    public EmailMessage referenceId(Long referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Boolean getIsSent() {
        return this.isSent;
    }

    public EmailMessage isSent(Boolean isSent) {
        this.isSent = isSent;
        return this;
    }

    public void setIsSent(Boolean isSent) {
        this.isSent = isSent;
    }

    public Instant getSentTime() {
        return this.sentTime;
    }

    public EmailMessage sentTime(Instant sentTime) {
        this.sentTime = sentTime;
        return this;
    }

    public void setSentTime(Instant sentTime) {
        this.sentTime = sentTime;
    }

    public String getComment() {
        return this.comment;
    }

    public EmailMessage comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<MailRecipient> getRecipients() {
        return this.recipients;
    }

    public EmailMessage recipients(Set<MailRecipient> mailRecipients) {
        this.setRecipients(mailRecipients);
        return this;
    }

    public EmailMessage addRecipient(MailRecipient mailRecipient) {
        this.recipients.add(mailRecipient);
        mailRecipient.setRecipients(this);
        return this;
    }

    public EmailMessage removeRecipient(MailRecipient mailRecipient) {
        this.recipients.remove(mailRecipient);
        mailRecipient.setRecipients(null);
        return this;
    }

    public void setRecipients(Set<MailRecipient> mailRecipients) {
        if (this.recipients != null) {
            this.recipients.forEach(i -> i.setRecipients(null));
        }
        if (mailRecipients != null) {
            mailRecipients.forEach(i -> i.setRecipients(this));
        }
        this.recipients = mailRecipients;
    }

    public Long getAttemptsNo() {
        return attemptsNo;
    }

    public void setAttemptsNo(Long attemptsNo) {
        this.attemptsNo = attemptsNo;
    }

    public EmailMessage attemptNo(Long attemptsNo) {
        this.attemptsNo = attemptsNo;
        return this;
    }


    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailMessage)) {
            return false;
        }
        return id != null && id.equals(((EmailMessage) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
            "id=" + id +
            ", from='" + from + '\'' +
            ", subject='" + subject + '\'' +
            ", content='" + content + '\'' +
            ", referenceId='" + referenceId + '\'' +
            ", isSent=" + isSent +
            ", sentTime=" + sentTime +
            ", attemptsNo=" + attemptsNo +
            ", comment='" + comment + '\'' +
            ", recipients=" + recipients +
            '}';
    }
}
