package sunshare.services;

import java.util.ArrayList;
import java.util.UUID;

import sunshare.entities.offer.Offer;
import sunshare.entities.offer.OfferStatus;
import sunshare.entities.proposal.Proposal;
import sunshare.entities.proposal.ProposalStatus;
import sunshare.json.manager.JsonFiles;
import sunshare.json.manager.JsonManager;

public class ProposalService extends BaseService {
    private final JsonManager offerJsonManager;
    private final NotificationService notificationService;

    public ProposalService() {
        super(JsonFiles.proposals);
        offerJsonManager = new JsonManager(JsonFiles.offers);
        notificationService = new NotificationService();
    }

    public Proposal create(String buyerUuid, String supplierUuid, String offerUuid, double proposalValue) {
        final Proposal proposal = new Proposal(generateUuid(), buyerUuid, supplierUuid, offerUuid, proposalValue,
                ProposalStatus.open);

        return jsonManager.insert(Proposal.class, jsonManager.toJsonNode(proposal));
    }

    public void reject(String proposalUuid) {
        jsonManager.update(Proposal.class, p -> {
            return p.getProposalUuid().equals(proposalUuid);
        }, u -> {
            u.setStatus(ProposalStatus.rejected);
            return u;
        });
    }

    private Proposal setProposalStatusToAccepted(String proposalUuid) {
        final ArrayList<Proposal> result = jsonManager.update(Proposal.class, p -> {
            return p.getProposalUuid().equals(proposalUuid);
        }, u -> {
            u.setStatus(ProposalStatus.accepted);
            return u;
        });

        if (result.isEmpty()) {
            return null;
        }

        return result.getFirst();
    }

    private void setProposalsStatusToSameOfferAsRejected(Proposal proposal) {
        jsonManager.update(Proposal.class, p -> {
            final String offerUuid = p.getOfferUuid();

            return offerUuid.equals(proposal.getOfferUuid()) && !p.getProposalUuid().equals(proposal.getProposalUuid());
        }, u -> {
            u.setStatus(ProposalStatus.rejected);
            return u;
        });
    }

    private void updateOfferStatusToSoldByProposal(Proposal proposal) {
        offerJsonManager.update(Offer.class, o -> {
            return o.getUuid().equals(proposal.getOfferUuid());
        }, u -> {
            u.setBuyerUuid(proposal.getBuyerUuid());
            u.setStatus(OfferStatus.sold);
            u.setAcceptedValue(proposal.getProposalValue());
            return u;
        });
    }

    public void accept(String proposalUuid) {
        // Define a proposta com o status de aceita
        final Proposal proposal = setProposalStatusToAccepted(proposalUuid);
        if (proposal == null) {
            return;
        }
        // Fecha as outras propostas para a mesma oferta
        setProposalsStatusToSameOfferAsRejected(proposal);
        // Atualiza o status da oferta para vendida
        updateOfferStatusToSoldByProposal(proposal);
    }

    public Proposal getByUuid(String uuid) {
        final ArrayList<Proposal> result = jsonManager.select(Proposal.class, p -> {
            return p.getProposalUuid().equals(uuid);
        });

        if (result.isEmpty()) {
            return null;
        }

        return result.getFirst();
    }

    public ArrayList<Proposal> getAllByBuyerUuid(String buyerUuid) {
        return jsonManager.select(Proposal.class, p -> {
            return p.getBuyerUuid().equals(buyerUuid);
        });
    }

    public ArrayList<Proposal> getAllBySupplierUuid(String supplierUuid) {
        return jsonManager.select(Proposal.class, p -> {
            return p.getSupplierUuid().equals(supplierUuid);
        });
    }

    public ArrayList<Proposal> getAllByOfferUuid(String offerUuid) {
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