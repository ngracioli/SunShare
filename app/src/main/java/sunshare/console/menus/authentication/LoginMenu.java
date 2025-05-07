package sunshare.console.menus.authentication;

import java.util.Scanner;

import sunshare.console.menus.entry.EntryMenu;
import sunshare.console.menus.main.MainMenu;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;
import sunshare.services.AuthService;

public class LoginMenu {
    private static boolean exit = false;

    public static void show(Scanner scanner, AuthService authService) {
        while (!exit) {
            showMenu(scanner, authService);
        }
    }

    public static void showMenu(Scanner scanner, AuthService authService) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Login");
        ConsoleUtils.printOption("Digite o e-mail: ");
        String email = scanner.nextLine();
        ConsoleUtils.printOption("Digite a senha: ");
        String password = scanner.nextLine();

        final User user = authService.login(email, password);

        if (user == null) {
            ConsoleUtils.printError("E-mail ou senha inválidos.");
            ConsoleUtils.timerConsole(2000);
            EntryMenu.show(scanner, authService);
            return;
        }

        ConsoleUtils.printSuccess("Login realizado com sucesso!");
        ConsoleUtils.timerConsole(2000);
        exit = true;
        MainMenu.show(scanner, user);
    }
}
