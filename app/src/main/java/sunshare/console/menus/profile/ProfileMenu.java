package sunshare.console.menus.profile;

import java.util.Scanner;

import sunshare.console.menus.ofert.OfertMenu;
import sunshare.console.menus.profile.infos.InfoMenu;
import sunshare.console.menus.utils.ConsoleUtils;
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
        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                break;
            case 2:
                new OfertMenu(scanner, user);
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
