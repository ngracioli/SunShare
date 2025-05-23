package sunshare.services;

import java.util.ArrayList;
import java.util.UUID;

import sunshare.entities.address.Address;
import sunshare.entities.document.Document;
import sunshare.entities.user.Buyer;
import sunshare.entities.user.Supplier;
import sunshare.entities.user.User;
import sunshare.json.manager.JsonFiles;

public class AuthService extends BaseService {
    public AuthService() {
        super(JsonFiles.users);
    }

    public Buyer registerBuyer(String name, String email, String password, Address address, Document document) {
        final User user = new Buyer(generateUuid(), name, email, password, address, document);

        return jsonManager.insert(Buyer.class, jsonManager.toJsonNode(user));
    }

    public Supplier registerSupplier(String name, String email, String password, Address address, Document document) {
        final Supplier user = new Supplier(generateUuid(), name, email, password, address, document);

        return jsonManager.insert(Supplier.class, jsonManager.toJsonNode(user));
    }

    public User login(String email, String password) {
        final ArrayList<User> result = jsonManager.select(User.class, user -> {
            return user.getEmail().equals(email) && user.getPassword().equals(password);
        });

        if (result.isEmpty()) {
            return null;
        }

        final User user = result.getFirst();

        if (user.isSupplier()) {
            return user.toSupplier();
        }

        return user.toBuyer();
    }

}
