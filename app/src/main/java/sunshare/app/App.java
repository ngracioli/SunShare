package sunshare.app;

import java.util.Scanner;

import sunshare.entities.Address;
import sunshare.entities.Document;
import sunshare.entities.User;
import sunshare.services.AuthService;
import sunshare.console.ConsoleUtils;
import sunshare.console.menus.*;

public class App {

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);

        EntryMenu.show(scanner, authService);
    }
}
