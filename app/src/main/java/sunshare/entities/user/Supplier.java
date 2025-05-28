package sunshare.entities.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import sunshare.entities.address.Address;
import sunshare.entities.document.Document;

public class Supplier extends User {
    @JsonCreator
    public Supplier(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("totalSales") int totalSales,
            @JsonProperty("totalPurchases") int totalPurchases,
            @JsonProperty("address") Address address,
            @JsonProperty("document") Document document) {
        super(uuid, name, email, password, totalSales, totalPurchases, true, address, document);
    };
}