package sunshare.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Buyer extends User {
    public final Document cpf;
    public final Document rg;

    @JsonCreator
    public Buyer(
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("address") Address address,
            @JsonProperty("cpf") Document cpf,
            @JsonProperty("rg") Document rg) {
        super(name, email, password, address);
        this.cpf = cpf;
        this.rg = rg;
    }

    public Document getCpf() {
        return cpf;
    }

    public Document getRg() {
        return rg;
    }
}
