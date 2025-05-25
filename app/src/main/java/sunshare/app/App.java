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

        try {
            new EntryMenu(scanner, authService);
        } catch (Exception e) {
            /**
             * Catch genérico para o app para lidar com interrupções de execução
             * tipo apertar Ctrl+C ou Ctrl+D
             */
            if (scanner.hasNext()) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        } finally {
            scanner.close();
        }
    }
}
