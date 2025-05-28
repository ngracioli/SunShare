package sunshare.console.menus.profile.ranking;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.user.User;
import sunshare.services.UserService;

public class UserRanking {
    public UserRanking(Scanner scanner, User user, boolean isSupplier) {
        UserService userService = new UserService();
        ArrayList<User> users = new ArrayList<>();

        if (isSupplier) {
            users = userService.getAllSuppliers();
            users.removeIf(u -> u.getTotalSales() <= 0);
            users.sort((u1, u2) -> Double.compare(u2.getTotalSales(), u1.getTotalSales()));
        } else {
            users = userService.getAllBuyers();
            users.removeIf(u -> u.getTotalPurchases() <= 0);
            users.sort((u1, u2) -> Double.compare(u2.getTotalPurchases(), u1.getTotalPurchases()));
        }

        ArrayList<User> top10 = new ArrayList<>();
        for (int i = 0; i < Math.min(10, users.size()); i++) {
            top10.add(users.get(i));
        }

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Ranking de " + (isSupplier ? "Fornecedores" : "Compradores") + " --");
        ConsoleUtils.printError("Pressione ENTER para voltar\n");
        if (top10.isEmpty()) {
            ConsoleUtils.printError("Nenhum usuário encontrada.");
        } else {
            for (int i = 0; i < top10.size(); i++) {
                User u = top10.get(i);
                String info = String.format("TOP %d. %s - Total de %s: %d",
                        i + 1, u.getName(),
                        isSupplier ? "Vendas" : "Compras",
                        isSupplier ? u.getTotalSales() : u.getTotalPurchases());
                if (i == 0) {
                    ConsoleUtils.printTop(i + 1, info);
                } else if (i == 1) {
                    ConsoleUtils.printTop(i + 1, info);
                } else if (i == 2) {
                    ConsoleUtils.printTop(i + 1, info);
                } else {
                    ConsoleUtils.printTop(0, info);
                }
            }
        }
        scanner.nextLine();
        return;
    }
}
