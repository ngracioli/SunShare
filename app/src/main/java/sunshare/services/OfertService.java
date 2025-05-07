package sunshare.services;

import sunshare.json.manager.JsonManager;
import sunshare.json.manager.JsonsFiles;

public class OfertService {

    private final JsonManager jsonManager;

    OfertService() {
        jsonManager = new JsonManager(JsonsFiles.oferts);
    }

    public void create() {

    }



}