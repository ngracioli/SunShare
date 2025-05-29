package sunshare.console.menus.profile.sales;

import java.util.Scanner;
import java.util.ArrayList;

import sunshare.entities.user.User;
import sunshare.services.OfferService;
import sunshare.services.ProposalService;
import sunshare.services.UserService;
import sunshare.entities.offer.Offer;
import sunshare.entities.proposal.Proposal;
import sunshare.entities.proposal.ProposalStatus;
import sunshare.console.menus.utils.ConsoleUtils;

public class UserSales {

    public UserSales(Scanner scanner, User user) {
        ProposalService proposalService = new ProposalService();
        OfferService offerService = new OfferService();
        UserService userService = new UserService();
        ArrayList<Proposal> sales = proposalService.getAllBySupplierUuid(user.getUuid());
        sales.removeIf(sale -> sale.getStatus() != ProposalStatus.accepted);

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Histórico de Vendas --");
        ConsoleUtils.printError("Pressione ENTER para voltar\n");
        if (sales.isEmpty()) {
            ConsoleUtils.printError("Nenhuma venda encontrada.");
        } else {
            for (int i = 0; i < sales.size(); i++) {

                Proposal sale = sales.get(i);
                Offer offer = offerService.getByUuid(sale.getOfferUuid());
                User buyer = userService.getByUuid(sale.getBuyerUuid());

                ConsoleUtils.printSuccess(String.format("Venda %d:", i + 1));
                String saleInfo = String.format(
                        "Comprador: %s\n" +
                                "Produto: %s %s\n" +
                                "Valor final: R$ %.2f\n",
                        buyer.getName(),
                        offer.getEnergy().getQuantity(),
                        offer.getEnergy().getSymbol(),
                        sale.getProposalValue());
                ConsoleUtils.printOption(saleInfo);
            }
        }
        scanner.nextLine();
        return;
    }
}
