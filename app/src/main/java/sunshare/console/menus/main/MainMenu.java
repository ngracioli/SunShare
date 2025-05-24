package sunshare.console.menus.main;

import java.util.Scanner;

import sunshare.console.ascii.LogoPrinter;
import sunshare.console.menus.offer.CreateOfferMenu;
import sunshare.console.menus.offer.OfferMenu;
import sunshare.console.menus.offer.OfferType;
import sunshare.console.menus.profile.ProfileMenu;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;

public class MainMenu {
    private boolean exit = false;

    public MainMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        new LogoPrinter();
        ConsoleUtils.printTitle("Bem-vindo, " + user.getName() + "!");
        ConsoleUtils.printExit("0. Sair");
        ConsoleUtils.printOption("1. Perfil");
        ConsoleUtils.printOption("2. Anuncios");
        if (user.isSupplier()) {
            ConsoleUtils.printOption("3. Criar Oferta");
        }
        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                new ProfileMenu(scanner, user);
                break;
            case 2:
                if (user.isSupplier()) {
                    new OfferType(scanner, user);
                    break;
                }
                new OfferMenu(scanner, user);
                break;
            case 3:
                if (user.isSupplier()) {
                    new CreateOfferMenu(scanner, user);
                    break;
                }
            default:
                ConsoleUtils.printError("Opção inválida.");
                ConsoleUtils.timerConsole(2000);
                break;
        }
    }

}
