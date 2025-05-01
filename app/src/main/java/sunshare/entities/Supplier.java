package sunshare.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Supplier extends User {
    @JsonCreator
    public Supplier(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("address") Address address,
            @JsonProperty("document") Document document) {
        super(uuid, name, email, password, true, address, document);
    };
}