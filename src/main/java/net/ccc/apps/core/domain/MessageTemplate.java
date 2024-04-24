package net.ccc.apps.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MessageTemplate.
 */
@Entity
@Table(name = "message_template")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MessageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "message_id")
    private String messageId;

    @Column(name = "subject")
    private String subject;

    @Column(name = "body")
    private String body;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MessageTemplate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public MessageTemplate messageId(String messageId) {
        this.setMessageId(messageId);
        return this;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return this.subject;
    }

    public MessageTemplate subject(String subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }

    public MessageTemplate body(String body) {
        this.setBody(body);
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageTemplate)) {
            return false;
        }
        return id != null && id.equals(((MessageTemplate) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageTemplate{" +
            "id=" + getId() +
            ", messageId='" + getMessageId() + "'" +
            ", subject='" + getSubject() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
}
