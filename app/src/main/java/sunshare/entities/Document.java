package sunshare.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {

    private String documentType;
    private String document;

    @JsonCreator
    public Document(
            @JsonProperty("documentType") String documentType,
            @JsonProperty("document") String document) {
        this.documentType = documentType;
        this.document = document;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

}
