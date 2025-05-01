package sunshare.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private String uuid;
    private String name;
    private String email;
    private String password;
    private boolean isSupplier;
    private Address address;
    private Document document;

    @JsonCreator
    public User(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("isSupplier") boolean isSupplier,
            @JsonProperty("address") Address address,
            @JsonProperty("document") Document document) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSupplier= isSupplier;
        this.address = address;
        this.document = document;
    }

    public String getUuid() {
        return uuid;
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

    public boolean isSupplier() {
        return this.isSupplier;
    }

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setSupplier(boolean isSupplier) {
		this.isSupplier = isSupplier;
	}

    @JsonIgnore
    public Supplier toSupplier() {
        return new Supplier(
                this.uuid,
                this.name,
                this.email,
                this.password,
                this.address,
                this.document);
    }

    @JsonIgnore
    public Buyer toBuyer() {
        return new Buyer(
                this.uuid,
                this.name,
                this.email,
                this.password,
                this.address,
                this.document);
    }
}
