package sunshare.console.menus.offer;

import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;

public class OfferMenu {
    private boolean exit = false;

    public OfferMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
    }
}
