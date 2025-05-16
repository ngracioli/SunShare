package sunshare.services;

import java.util.ArrayList;
import java.util.UUID;

import sunshare.entities.energy.Energy;
import sunshare.entities.offer.Offer;
import sunshare.entities.offer.OfferStatus;
import sunshare.json.manager.JsonManager;
import sunshare.json.manager.JsonsFiles;

public class OfferService {

    private final JsonManager jsonManager;

    public OfferService() {
        jsonManager = new JsonManager(JsonsFiles.offers);
    }

    public ArrayList<Offer> getAll() {
        return jsonManager.select(Offer.class, o -> true);
    }

    public ArrayList<Offer> getAllFromUser(String userUuid) {
        return jsonManager.select(Offer.class, o -> o.getSupplierUuid().equals(userUuid));
    }

    public void create(String supplierUuid, Energy energy) {
        final Offer offer = new Offer(UUID.randomUUID().toString(), supplierUuid, energy, OfferStatus.open);

        jsonManager.insert(Offer.class, jsonManager.toJsonNode(offer));
    }

    public void delete(String uuid) {
        jsonManager.delete(Offer.class, o -> {
            return o.getUuid().equals(uuid);
        });
    }

}