package sunshare.console.menus.profile.infos;

import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.address.Address;
import sunshare.entities.document.Document;
import sunshare.entities.offer.OfferStatus;
import sunshare.entities.user.User;
import sunshare.services.OfferService;
import sunshare.services.UserService;

public class InfoMenu {
    int quantityOfOffers = 0;

    public InfoMenu(Scanner scanner, User userOld) {
        UserService userService = new UserService();
        User user = userService.getByUuid(userOld.getUuid());
        final Address address = user.getAddress();
        final Document document = user.getDocument();

        if (user.isSupplier()) {
            OfferService offerService = new OfferService();
            quantityOfOffers = 0;
            offerService.getAllFromUser(user.getUuid()).forEach(offer -> {
                if (offer.getStatus() == OfferStatus.open) {
                    quantityOfOffers++;
                }
            });
        }

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Minhas informações --");
        ConsoleUtils.printSuccess("Nome: " + user.getName());
        ConsoleUtils.printSuccess("Email: " + user.getEmail());
        ConsoleUtils.printSuccess("Tipo: " + (user.isSupplier() ? "Fornecedor" : "Consumidor"));
        ConsoleUtils.printSuccess("Compras realizadas: " + user.getTotalPurchases());
        if (user.isSupplier()) {
            ConsoleUtils.printSuccess("Ofertas abertos: " + quantityOfOffers);
            ConsoleUtils.printSuccess("Vendas efetuadas: " + user.getTotalSales());
        }
        ConsoleUtils.printSuccess("Endereço: " + address.getStreet() + ", " + address.getNeighborhood() + ", "
                + address.getCity() + ", " + address.getState() + ", " + address.getCep());
        ConsoleUtils.printSuccess("Tipo de documento: " + document.getDocumentType().type);
        ConsoleUtils.printSuccess("Número do documento: " + document.getDocument());
        ConsoleUtils.printError("Pressione ENTER para voltar");
        scanner.nextLine();
        return;
    }
}
