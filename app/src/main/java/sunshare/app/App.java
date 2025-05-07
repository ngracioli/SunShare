package sunshare.app;

import java.util.Scanner;

import sunshare.entities.address.Address;
import sunshare.entities.document.Document;
import sunshare.entities.user.User;
import sunshare.services.AuthService;
import sunshare.console.menus.*;
import sunshare.console.menus.entry.EntryMenu;
import sunshare.console.menus.utils.ConsoleUtils;

public class App {

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);

        EntryMenu.show(scanner, authService);
    }
}
