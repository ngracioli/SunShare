package sunshare.console.menus.profile.proposals.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.proposal.Proposal;
import sunshare.entities.proposal.ProposalStatus;
import sunshare.entities.user.User;
import sunshare.entities.offer.Offer;
import sunshare.services.OfferService;
import sunshare.services.ProposalService;
import sunshare.services.UserService;

public class UserProposals {
    public UserProposals(Scanner scanner, User user) {
        ProposalService proposalService = new ProposalService();
        OfferService offerService = new OfferService();
        UserService userService = new UserService();
        ArrayList<Proposal> proposals = proposalService.getAllByBuyerUuid(user.getUuid());

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Propostas de " + user.getName() + "--");
        ConsoleUtils.printError("Pressione ENTER para voltar\n");
        if (proposals.isEmpty()) {
            ConsoleUtils.printError("Nenhuma proposta encontrada.");
        } else {
            for (int i = 0; i < proposals.size(); i++) {

                Proposal proposal = proposals.get(i);
                Offer offer = offerService.getByUuid(proposal.getOfferUuid());
                User supllier = userService.getByUuid(offer.getSupplierUuid());

                ConsoleUtils.printSuccess(String.format("Proposta %d:", i + 1));
                String proposalInfo = String.format(
                        "Fornecedor: %s\n" +
                                "Produto: %s %s\n" +
                                "Valor oferecido: R$ %.2f",
                        supllier.getName(),
                        offer.getEnergy().getQuantity(),
                        offer.getEnergy().getSymbol(),
                        proposal.getProposalValue());
                ConsoleUtils.printOption(proposalInfo);

                final Map<ProposalStatus, String> mapLabels = new HashMap<>();
                mapLabels.put(ProposalStatus.open, "Pendente");
                mapLabels.put(ProposalStatus.accepted, "Aceita");
                mapLabels.put(ProposalStatus.rejected, "Rejeitada");

                final String label = mapLabels.get(proposal.getStatus());

                if (proposal.getStatus() == ProposalStatus.open) {
                    ConsoleUtils.printWaiting("Status: " + label + "\n");
                } else if (proposal.getStatus() == ProposalStatus.accepted) {
                    ConsoleUtils.printApproved("Status: " + label + "\n");
                } else if (proposal.getStatus() == ProposalStatus.rejected) {
                    ConsoleUtils.printError("Status: " + label + "\n");
                }

            }
        }
        scanner.nextLine();
        return;
    }
}
