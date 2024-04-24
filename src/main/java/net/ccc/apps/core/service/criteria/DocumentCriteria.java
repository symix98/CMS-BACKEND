package net.ccc.apps.core.service.criteria;

import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.UUIDFilter;
import tech.jhipster.service.filter.Filter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link net.ccc.apps.core.domain.Document} entity. This class is used
 * in {@link net.ccc.apps.core.web.rest.DocumentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /documents?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DocumentCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private StringFilter documentId;

    private UUIDFilter serviceId;

    private StringFilter serviceURI;

    private StringFilter title;

    private StringFilter description;

    private StringFilter version;

    private StringFilter mimeType;

    private StringFilter folder;

    private StringFilter formType;

    private StringFilter formId;

    private InstantFilter creationDate;

    private StringFilter createdBy;

    private InstantFilter modificationDate;

    private StringFilter modifiedBy;

    public DocumentCriteria() {
    }

    public DocumentCriteria(DocumentCriteria other) {
         this.documentId = other.documentId == null ? null : other.documentId.copy();
        this.serviceId = other.serviceId == null ? null : other.serviceId.copy();
        this.serviceURI = other.serviceURI == null ? null : other.serviceURI.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.version = other.version == null ? null : other.version.copy();
        this.mimeType = other.mimeType == null ? null : other.mimeType.copy();
        this.folder = other.folder == null ? null : other.folder.copy();
        this.formType = other.formType == null ? null : other.formType.copy();
        this.formId = other.formId == null ? null : other.formId.copy();
        this.creationDate = other.creationDate == null ? null : other.creationDate.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.modificationDate = other.modificationDate == null ? null : other.modificationDate.copy();
        this.modifiedBy = other.modifiedBy == null ? null : other.modifiedBy.copy();
    }

    @Override
    public DocumentCriteria copy() {
        return new DocumentCriteria(this);
    }

    public StringFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(StringFilter documentId) {
        this.documentId = documentId;
    }

    public UUIDFilter getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUIDFilter serviceId) {
        this.serviceId = serviceId;
    }

    public StringFilter getServiceURI() {
        return serviceURI;
    }

    public void setServiceURI(StringFilter serviceURI) {
        this.serviceURI = serviceURI;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getVersion() {
        return version;
    }

    public void setVersion(StringFilter version) {
        this.version = version;
    }

    public StringFilter getMimeType() {
        return mimeType;
    }

    public void setMimeType(StringFilter mimeType) {
        this.mimeType = mimeType;
    }

    public StringFilter getFolder() {
        return folder;
    }

    public void setFolder(StringFilter folder) {
        this.folder = folder;
    }

    public StringFilter getFormType() {
        return formType;
    }

    public void setFormType(StringFilter formType) {
        this.formType = formType;
    }

    public StringFilter getFormId() {
        return formId;
    }

    public void setFormId(StringFilter formId) {
        this.formId = formId;
    }

    public InstantFilter getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(InstantFilter creationDate) {
        this.creationDate = creationDate;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public InstantFilter getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(InstantFilter modificationDate) {
        this.modificationDate = modificationDate;
    }

    public StringFilter getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(StringFilter modifiedBy) {
        this.modifiedBy = modifiedBy;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DocumentCriteria that = (DocumentCriteria) o;
        return
             Objects.equals(documentId, that.documentId) &&
            Objects.equals(serviceId, that.serviceId) &&
            Objects.equals(serviceURI, that.serviceURI) &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(version, that.version) &&
            Objects.equals(mimeType, that.mimeType) &&
            Objects.equals(folder, that.folder) &&
            Objects.equals(formType, that.formType) &&
            Objects.equals(formId, that.formId) &&
            Objects.equals(creationDate, that.creationDate) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(modificationDate, that.modificationDate) &&
            Objects.equals(modifiedBy, that.modifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
         documentId,
        serviceId,
        serviceURI,
        title,
        description,
        version,
        mimeType,
        folder,
        formType,
        formId,
        creationDate,
        createdBy,
        modificationDate,
        modifiedBy
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentCriteria{" +
                 (documentId != null ? "documentId=" + documentId + ", " : "") +
                (serviceId != null ? "serviceId=" + serviceId + ", " : "") +
                (serviceURI != null ? "serviceURI=" + serviceURI + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (version != null ? "version=" + version + ", " : "") +
                (mimeType != null ? "mimeType=" + mimeType + ", " : "") +
                (folder != null ? "folder=" + folder + ", " : "") +
                (formType != null ? "formType=" + formType + ", " : "") +
                (formId != null ? "formId=" + formId + ", " : "") +
                (creationDate != null ? "creationDate=" + creationDate + ", " : "") +
                (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
                (modificationDate != null ? "modificationDate=" + modificationDate + ", " : "") +
                (modifiedBy != null ? "modifiedBy=" + modifiedBy + ", " : "") +
            "}";
    }

}
