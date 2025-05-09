package sunshare.app;

import java.util.Scanner;

import sunshare.services.AuthService;
import sunshare.console.menus.entry.EntryMenu;

public class App {

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);

        new EntryMenu(scanner, authService);
    }
}
