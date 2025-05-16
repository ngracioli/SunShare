package sunshare.console.menus.profile;

import java.util.Scanner;

import sunshare.console.menus.offer.OfferMenu;
import sunshare.console.menus.profile.infos.InfoMenu;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.user.User;

public class ProfileMenu {
    private boolean exit = false;

    public ProfileMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printExit("0. Voltar");
        ConsoleUtils.printOption("1. Minha informações");
        ConsoleUtils.printOption("2. Compras");
        if (user.isSupplier()) {
            ConsoleUtils.printOption("3. Vendas");
        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite 0, 1, 2 ou 3.", 0, 1, 2, 3);
        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                break;
            case 2:
                new OfferMenu(scanner, user);
                break;
            case 3:
                if (user.isSupplier()) {
                    ConsoleUtils.printOption("VC É VENDEDOR");
                    ConsoleUtils.timerConsole(2000);
                    break;
                }
            default:
                ConsoleUtils.printError("Opção inválida.");
                ConsoleUtils.timerConsole(2000);
                break;
        }
    }
}
