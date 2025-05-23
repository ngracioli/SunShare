package sunshare.entities.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import sunshare.entities.address.Address;
import sunshare.entities.document.Document;

public class Buyer extends User {
    @JsonCreator
    public Buyer(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("address") Address address,
            @JsonProperty("document") Document document) {
        super(uuid, name, email, password, false, address, document);
    }
}
