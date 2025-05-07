package sunshare.console.menus.profile;

import java.util.Scanner;

import sunshare.entities.user.User;

public class ProfileMenu {
    private static boolean exit = false;

    public static void show(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public static void showMenu(Scanner scanner, User user) {
    }
}
