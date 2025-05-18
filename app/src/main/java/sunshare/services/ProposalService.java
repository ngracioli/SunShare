package sunshare.services;

import java.util.ArrayList;
import java.util.UUID;

import sunshare.entities.proposal.Proposal;
import sunshare.json.manager.JsonFiles;
import sunshare.json.manager.JsonManager;

public class ProposalService {
    private final JsonManager jsonManager;

    public ProposalService() {
        jsonManager = new JsonManager(JsonFiles.proposals);
    }

    public Proposal create(String buyerUuid, String supplierUuid, String offerUuid) {
        final Proposal proposal = new Proposal(UUID.randomUUID().toString(), buyerUuid, supplierUuid, offerUuid);

        return jsonManager.insert(Proposal.class, jsonManager.toJsonNode(proposal));
    }

    public Proposal get(String uuid) {
        final ArrayList<Proposal> result = jsonManager.select(Proposal.class, p -> {
            return p.getProposalUuid().equals(uuid);
        });

        if (result.isEmpty()) {
            return null;
        }
        
        return result.getFirst();
    }

    public ArrayList<Proposal> getAllFromBuyer(String buyerUuid) {
        return jsonManager.select(Proposal.class, p -> {
            return p.getBuyerUuid().equals(buyerUuid);
        });
    }

    public ArrayList<Proposal> getAllFromSupplier(String supplierUuid) {
        return jsonManager.select(Proposal.class, p -> {
            return p.getSupplierUuid().equals(supplierUuid);
        });
    }

    public ArrayList<Proposal> getAllFromOffer(String offerUuid) {
        return jsonManager.select(Proposal.class, p -> {
            return p.getOfferUuid().equals(offerUuid);
        });
    }

    public void cancel(String uuid) {
        jsonManager.delete(Proposal.class, p -> {
            return p.getProposalUuid().equals(uuid);
        });
    }

}