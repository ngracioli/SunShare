package sunshare.entities.document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {
    private DocumentTypes documentType;
    private String document;

    @JsonCreator
    public Document(
            @JsonProperty("documentType") DocumentTypes documentType,
            @JsonProperty("document") String document) {
        this.documentType = documentType;
        this.document = document;
    }

    public DocumentTypes getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentTypes documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
