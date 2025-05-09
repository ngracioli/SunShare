package sunshare.console.menus.ofert;

import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;

public class OfertMenu {
    private boolean exit = false;

    public OfertMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
    }
}
