package sunshare.console.menus.main;

import java.util.Scanner;

import sunshare.console.menus.ofert.OfertMenu;
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
                ConsoleUtils.exit();
                exit = true;
                break;
            case 1:
                new ProfileMenu(scanner, user);
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
