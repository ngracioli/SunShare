package sunshare.console.menus;

import java.util.Scanner;
import sunshare.console.ConsoleUtils;
import sunshare.services.AuthService;
import sunshare.console.menus.authentication.LoginMenu;
import sunshare.console.menus.authentication.RegisterMenu;

public class EntryMenu {
    private static boolean exit = false;

    public static void show(Scanner scanner, AuthService authService) {
        while (!exit) {
            showMenu(scanner, authService);
        }
    }

    public static void showMenu(Scanner scanner, AuthService authService) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Bem-vindo ao SunShare");
        ConsoleUtils.printExit("0. Sair");
        ConsoleUtils.printOption("1. Cadastrar");
        ConsoleUtils.printOption("2. Entrar");

        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 0:
                ConsoleUtils.exit();
                exit = true;
                break;
            case 1:
                RegisterMenu.show(scanner, authService);
                break;
            case 2:
                LoginMenu.show(scanner, authService);
                break;
            default:
                ConsoleUtils.printError("Opção inválida. Tente novamente.");
                ConsoleUtils.timerConsole(2000);
                break;
        }
    }
}
