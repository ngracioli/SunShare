package sunshare;

public class User {
    private String name;
    private String email;
    private String password;
    private Address address;
    private Document document;

    public User(String name, String email, String password, Address address, Document document) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.document = document;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
