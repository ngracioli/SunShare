package sunshare.console;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleUtils {
    static {
        AnsiConsole.systemInstall();
    }

    public static void uninstall() {
        AnsiConsole.systemUninstall();
    }

    public static void printTitle(String title) {
        System.out.println(ansi().fgBrightYellow().bold().a(title).reset());
    }

    public static void printOption(String option) {
        System.out.println(ansi().fgBrightCyan().a(option).reset());
    }

    public static void printSuccess(String message) {
        System.out.println(ansi().fgBrightGreen().bold().a(message).reset());
    }

    public static void printError(String message) {
        System.out.println(ansi().fgBrightRed().bold().a(message).reset());
    }

    public static void printExit(String message) {
        printError(message);
    }

    public static void timerConsole(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erro no timer");
        }
    }

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar o console");
        }
    }

    public static void exit() {
        ConsoleUtils.printExit("Saindo...");
        ConsoleUtils.timerConsole(2000);
        System.exit(0);
    }
}
