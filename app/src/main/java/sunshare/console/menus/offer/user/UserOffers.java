package sunshare.console.menus.offer.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.offer.Offer;
import sunshare.entities.offer.OfferStatus;
import sunshare.entities.user.User;
import sunshare.services.OfferService;
import sunshare.services.UserService;

public class UserOffers {
    public UserOffers(Scanner scanner, User user) {
        OfferService offerService = new OfferService();
        UserService userService = new UserService();

        ArrayList<Offer> offers = offerService.getAllFromUser(user.getUuid());
        offers.removeIf(offer -> offer.getStatus() != OfferStatus.open);

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Ofertas abertas de " + user.getName() + "--");
        ConsoleUtils.printError("Pressione ENTER para voltar\n");
        if (offers.isEmpty()) {
            ConsoleUtils.printError("Nenhuma oferta encontrada.");
        } else {
            for (int i = 0; i < offers.size(); i++) {

                Offer offer = offers.get(i);

                ConsoleUtils.printSuccess(String.format("Oferta %d:", i + 1));
                String offerInfo = String.format(
                        "Produto: %s %s\n" +
                                "Valor pedido: R$ %.2f\n",
                        offer.getEnergy().getQuantity(),
                        offer.getEnergy().getSymbol(),
                        offer.getEnergy().getAmount());
                ConsoleUtils.printOption(offerInfo);
            }
        }
        scanner.nextLine();
        return;
    }
}
