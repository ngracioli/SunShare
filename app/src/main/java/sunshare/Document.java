package sunshare;

public class Document {

    private String documentType;
    private String document;

    public Document(String documentType, String document) {
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
