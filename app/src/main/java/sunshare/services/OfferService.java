package sunshare.services;

import java.util.ArrayList;
import java.util.UUID;

import sunshare.entities.energy.Energy;
import sunshare.entities.offer.Offer;
import sunshare.entities.offer.OfferStatus;
import sunshare.entities.proposal.Proposal;
import sunshare.json.manager.JsonManager;
import sunshare.json.manager.JsonFiles;

public class OfferService extends BaseService {
    private final JsonManager proposalJsonManager;

    public OfferService() {
        super(JsonFiles.offers);
        proposalJsonManager = new JsonManager(JsonFiles.proposals);
    }

    public ArrayList<Offer> getAll() {
        return jsonManager.select(Offer.class, o -> true);
    }

    public ArrayList<Offer> getAllFromUser(String userUuid) {
        return jsonManager.select(Offer.class, o -> o.getSupplierUuid().equals(userUuid));
    }

    public Offer create(String supplierUuid, Energy energy) {
        final Offer offer = new Offer(UUID.randomUUID().toString(), supplierUuid, energy, OfferStatus.open);

        return jsonManager.insert(Offer.class, jsonManager.toJsonNode(offer));
    }

    public void delete(String uuid) {
        jsonManager.delete(Offer.class, o -> {
            return o.getUuid().equals(uuid);
        });

        proposalJsonManager.delete(Proposal.class, p -> {
            return p.getOfferUuid().equals(uuid);
        });
    }

}