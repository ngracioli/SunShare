package sunshare.console.menus.offer;

import java.util.Scanner;

import sunshare.console.menus.offer.user.UserOffers;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;
import sunshare.console.menus.utils.InputUtils;

public class OfferType {
    private boolean exit = false;

    public OfferType(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Menu de Oferta --");
        ConsoleUtils.printExit("0. Voltar");
        ConsoleUtils.printOption("1. Ofertas Disponíveis");
        ConsoleUtils.printOption("2. Suas Ofertas");

        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ",
                "Digite 0 para voltar ou o número de uma opção.", InputUtils.range(0, 2));

        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                new OfferMenu(scanner, user);
                break;
            case 2:
                new UserOffers(scanner, user);
                break;
            default:
                ConsoleUtils.printError("Opção inválida. Tente novamente.");
        }
    }
}
