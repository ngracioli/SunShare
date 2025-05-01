package sunshare.console.menus;

import java.util.Scanner;
import sunshare.console.ConsoleUtils;
import sunshare.services.AuthService;
import sunshare.console.menus.authentication.LoginMenu;
import sunshare.console.menus.authentication.RegisterMenu;

public class EntryMenu {
    public static void show(Scanner scanner, AuthService authService) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Bem-vindo ao SunShare");
        ConsoleUtils.printOption("1. Cadastrar");
        ConsoleUtils.printOption("2. Entrar");
        ConsoleUtils.printExit("3. Sair");

        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                RegisterMenu.show(scanner, authService);
                break;
            case 2:
                LoginMenu.show(scanner, authService);
                break;
            case 3:
                ConsoleUtils.printExit("Saindo...");
                ConsoleUtils.timerConsole(2000);
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                ConsoleUtils.timerConsole(2000);
        }
    }
}
