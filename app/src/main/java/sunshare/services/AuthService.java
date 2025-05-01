package sunshare.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import sunshare.entities.Address;
import sunshare.entities.Document;
import sunshare.entities.User;
import sunshare.entities.Buyer;
import sunshare.entities.Supplier;
import sunshare.json.manager.JsonsFiles;
import sunshare.json.manager.JsonManager;

public class AuthService {

    private final JsonManager jsonManager;

    public AuthService() {
        this.jsonManager = new JsonManager(JsonsFiles.users);
    }

    public Buyer registerBuyer(String name, String email, String password, Address address, Document document) {
        final User user = new Buyer(UUID.randomUUID().toString(), name, email, password, address, document);

        return jsonManager.insert(Buyer.class, jsonManager.toJsonNode(user));
    }

    public Supplier registerSupplier(String name, String email, String password, Address address, Document document) {
        final Supplier user = new Supplier(UUID.randomUUID().toString(), name, email, password, address, document);

        return jsonManager.insert(Supplier.class, jsonManager.toJsonNode(user));
    }

    public User login(String email, String password) {
        final ArrayList<User> result = jsonManager.select(User.class, user -> {
            return user.getEmail().equals(email) && user.getPassword().equals(password);
        });

        if (result.size() == 0) {
            return null;
        }

        final User user = result.get(0);

        if (user.isSupplier()) {
            return user.toSupplier();
        }

        return user.toBuyer();
    }

}
