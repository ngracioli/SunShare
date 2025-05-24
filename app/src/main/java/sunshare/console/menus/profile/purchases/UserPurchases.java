package sunshare.console.menus.profile.purchases;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;
import sunshare.services.OfferService;
import sunshare.services.ProposalService;
import sunshare.services.UserService;
import sunshare.entities.offer.Offer;
import sunshare.entities.proposal.Proposal;
import sunshare.entities.proposal.ProposalStatus;

public class UserPurchases {
    public UserPurchases(Scanner scanner, User user) {

        ProposalService proposalService = new ProposalService();
        OfferService offerService = new OfferService();
        UserService userService = new UserService();
        ArrayList<Proposal> proposals = proposalService.getAllByBuyerUuid(user.getUuid());
        proposals.removeIf(propsal -> propsal.getStatus() != ProposalStatus.accepted);

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Histórico de Compras --");
        ConsoleUtils.printError("Pressione ENTER para voltar\n");
        if (proposals.isEmpty()) {
            ConsoleUtils.printError("Nenhuma compra encontrada.");
        } else {
            for (int i = 0; i < proposals.size(); i++) {

                Proposal proposal = proposals.get(i);
                Offer offer = offerService.getByUuid(proposal.getOfferUuid());
                User supllier = userService.getByUuid(offer.getSupplierUuid());

                ConsoleUtils.printSuccess(String.format("Compra %d:", i + 1));
                String proposalInfo = String.format(
                        "Fornecedor: %s\n" +
                                "Produto: %s %s\n" +
                                "Valor final: R$ %.2f",
                        supllier.getName(),
                        offer.getEnergy().getQuantity(),
                        offer.getEnergy().getSymbol(),
                        proposal.getProposalValue());
                ConsoleUtils.printOption(proposalInfo);
            }
        }
        scanner.nextLine();
        return;
    }
}
