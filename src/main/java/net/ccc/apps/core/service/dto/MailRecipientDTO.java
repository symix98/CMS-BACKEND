package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ccc.app.lms.domain.MailRecipient} entity.
 */
public class MailRecipientDTO implements Serializable {

    private Long id;

    private String name;

    private String email;

    private EmailMessageDTO recipients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmailMessageDTO getRecipients() {
        return recipients;
    }

    public void setRecipients(EmailMessageDTO recipients) {
        this.recipients = recipients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MailRecipientDTO)) {
            return false;
        }

        MailRecipientDTO mailRecipientDTO = (MailRecipientDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, mailRecipientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MailRecipientDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", recipients=" + getRecipients() +
            "}";
    }
}
