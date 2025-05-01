package sunshare.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Buyer extends User {
    @JsonCreator
    public Buyer(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("address") Address address,
            @JsonProperty("document") Document document) {
        super(name, email, password, false, address, document);
    }
}
