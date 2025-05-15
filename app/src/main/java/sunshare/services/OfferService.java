package sunshare.services;

import java.util.ArrayList;
import java.util.UUID;

import sunshare.entities.energy.Energy;
import sunshare.entities.ofert.Ofert;
import sunshare.entities.ofert.OfertStatus;
import sunshare.json.manager.JsonManager;
import sunshare.json.manager.JsonsFiles;

public class OfertService {

    private final JsonManager jsonManager;

    public OfertService() {
        jsonManager = new JsonManager(JsonsFiles.oferts);
    }

    public ArrayList<Ofert> getAll() {
        return jsonManager.select(Ofert.class, o -> true);
    }

    public ArrayList<Ofert> getOpen() {
        return jsonManager.select(Ofert.class, o -> true);
    }

    public void create(String supplierUuid, Energy energy) {
        final Ofert ofert = new Ofert(UUID.randomUUID().toString(), supplierUuid, energy, OfertStatus.open);

        jsonManager.insert(Ofert.class, jsonManager.toJsonNode(ofert));
    }

    public void delete(String uuid) {
        jsonManager.delete(Ofert.class, o -> {
            return o.getUuid().equals(uuid);
        });
    }

}