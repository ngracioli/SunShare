package sunshare.entities;

public enum DocumentTypes {
    CPF("CPF"),
    CNPJ("CNPJ");

    public final String type;

    DocumentTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}