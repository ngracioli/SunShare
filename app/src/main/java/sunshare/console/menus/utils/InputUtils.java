package sunshare.console.menus.utils;

import java.util.Scanner;

public class InputUtils {
    public static int readIntOption(Scanner scanner, String prompt, String errorMsg, int... validOptions) {
        int value;
        boolean valid;
        do {
            System.out.println(prompt);
            while (!scanner.hasNextInt()) {
                ConsoleUtils.printError(errorMsg);
                scanner.next();
            }
            value = scanner.nextInt();
            valid = false;
            for (int v : validOptions) {
                if (value == v)
                    valid = true;
            }
            if (!valid)
                ConsoleUtils.printError(errorMsg);
        } while (!valid);
        scanner.nextLine();
        return value;
    }
}
