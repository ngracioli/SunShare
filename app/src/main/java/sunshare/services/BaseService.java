package sunshare.services;

import java.util.UUID;

import sunshare.json.manager.JsonFiles;
import sunshare.json.manager.JsonManager;

public class BaseService {
    protected final JsonManager jsonManager;

    public BaseService(JsonFiles file) {
        this.jsonManager = new JsonManager(file.getFile());
    }

    String generateUuid() {
        return UUID.randomUUID().toString();
    }

}
