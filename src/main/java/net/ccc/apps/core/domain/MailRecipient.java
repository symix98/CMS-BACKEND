package net.ccc.apps.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A MailRecipient.
 */
@Entity
@Table(name = "mail_recipient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MailRecipient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_recipient_gen")
    @SequenceGenerator(name = "mail_recipient_gen",allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JsonIgnoreProperties(value = { "recipients" }, allowSetters = true)
    private EmailMessage recipients;

    // jhipster-needle-entity-add-field - JHipster will add fields here


    public MailRecipient(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MailRecipient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MailRecipient id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public MailRecipient name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public MailRecipient email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailMessage getRecipients() {
        return this.recipients;
    }

    public MailRecipient recipients(EmailMessage emailMessage) {
        this.setRecipients(emailMessage);
        return this;
    }

    public void setRecipients(EmailMessage emailMessage) {
        this.recipients = emailMessage;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MailRecipient)) {
            return false;
        }
        return id != null && id.equals(((MailRecipient) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MailRecipient{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }

    // prettier-ignore

    public String recipientStyle() {
        return   getName()+"<"+getEmail()+">";

    }

}
