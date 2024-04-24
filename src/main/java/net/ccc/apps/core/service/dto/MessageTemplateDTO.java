package net.ccc.apps.core.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link net.ccc.apps.core.domain.MessageTemplate} entity.
 */
public class MessageTemplateDTO implements Serializable {

    private Long id;

    private String messageId;

    private String subject;

    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageTemplateDTO)) {
            return false;
        }

        MessageTemplateDTO messageTemplateDTO = (MessageTemplateDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, messageTemplateDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageTemplateDTO{" +
            "id=" + getId() +
            ", messageId='" + getMessageId() + "'" +
            ", subject='" + getSubject() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
}
