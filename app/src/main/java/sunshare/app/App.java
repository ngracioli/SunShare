package sunshare.app;

import sunshare.console.menus.entry.EntryMenu;
import sunshare.json.manager.JsonInitializer;
import sunshare.services.AuthService;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        JsonInitializer.initialize();

        final AuthService authService = new AuthService();
        final Scanner scanner = new Scanner(System.in);

        new EntryMenu(scanner, authService);
    }
}
