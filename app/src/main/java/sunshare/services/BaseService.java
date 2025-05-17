package sunshare.services;

import java.util.UUID;

import sunshare.json.manager.JsonManager;

public class BaseService {
    protected final JsonManager jsonManager;

    public BaseService(String file) {
        this.jsonManager = new JsonManager(file);
    }

    String generateUuid() {
        return UUID.randomUUID().toString();
    }

}
