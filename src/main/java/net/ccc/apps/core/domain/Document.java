package net.ccc.apps.core.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "document_id", nullable = false)
    private String documentId;

    @NotNull
    @Column(name = "service_id", nullable = false)
    private UUID serviceId;

    @Column(name = "service_uri")
    private String serviceURI;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "version")
    private String version;

    @NotNull
    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(name = "folder")
    private String folder;

    @Column(name = "form_type")
    private String formType;

    @Column(name = "form_id")
    private String formId;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private Instant creationDate;

    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "modification_date", nullable = false)
    private Instant modificationDate;

    @NotNull
    @Column(name = "modified_by", nullable = false)
    private String modifiedBy;

    public String getDocumentId() {
        return documentId;
    }

    public Document documentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public Document serviceId(UUID serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceURI() {
        return serviceURI;
    }

    public Document serviceURI(String serviceURI) {
        this.serviceURI = serviceURI;
        return this;
    }

    public void setServiceURI(String serviceURI) {
        this.serviceURI = serviceURI;
    }

    public String getTitle() {
        return title;
    }

    public Document title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Document description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public Document version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Document mimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFolder() {
        return folder;
    }

    public Document folder(String folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFormType() {
        return formType;
    }

    public Document formType(String formType) {
        this.formType = formType;
        return this;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormId() {
        return formId;
    }

    public Document formId(String formId) {
        this.formId = formId;
        return this;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Document creationDate(Instant creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Document createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getModificationDate() {
        return modificationDate;
    }

    public Document modificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public void setModificationDate(Instant modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Document modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return documentId != null && documentId.equals(((Document) o).documentId);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Document{" +
            ", documentId='" + getDocumentId() + "'" +
            ", serviceId='" + getServiceId() + "'" +
            ", serviceURI='" + getServiceURI() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", version='" + getVersion() + "'" +
            ", mimeType='" + getMimeType() + "'" +
            ", folder='" + getFolder() + "'" +
            ", formType='" + getFormType() + "'" +
            ", formId='" + getFormId() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", modificationDate='" + getModificationDate() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            "}";
    }
}
