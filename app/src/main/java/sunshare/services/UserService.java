package sunshare.services;

import java.util.ArrayList;

import sunshare.entities.user.User;
import sunshare.json.manager.JsonFiles;

public class UserService extends BaseService {
    public UserService() {
        super(JsonFiles.users);
    }

    public User getByUuid(String uuid) {
        final ArrayList<User> result = jsonManager.select(User.class, user -> {
            return user.getUuid().equals(uuid);
        });

        if (result.isEmpty()) {
            return null;
        }

        return result.getFirst();
    }

    public ArrayList<User> getAllSuppliers() {
        return jsonManager.select(User.class, m -> m.isSupplier());
    }

    public ArrayList<User> getAllBuyers() {
        return jsonManager.select(User.class, m -> !m.isSupplier());
    }

    public void delete(String uuid) {
        jsonManager.delete(User.class, user -> {
            return user.getUuid().equals(uuid);
        });
    }

}
