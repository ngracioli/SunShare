package sunshare.console.menus.profile;

import java.util.Scanner;

import sunshare.console.menus.profile.infos.InfoMenu;
import sunshare.console.menus.profile.notifications.UserNotification;
import sunshare.console.menus.profile.purchases.UserPurchases;
import sunshare.console.menus.profile.sales.UserSales;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.console.menus.profile.proposals.ProposalMenu;
import sunshare.console.menus.profile.proposals.user.UserProposals;
import sunshare.entities.user.User;
import sunshare.services.NotificationService;

public class ProfileMenu {
    private boolean exit = false;

    public ProfileMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {

        NotificationService notificationService = new NotificationService();
        final int notificationCount = notificationService.getQuantityOfNotifications(user.getUuid());

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Perfil --");
        ConsoleUtils.printExit("0. Voltar");
        ConsoleUtils.printOption("1. Minha informações");
        if (notificationCount > 0) {
            ConsoleUtils.printApproved("2. Notificações (" + notificationCount + ")");
        } else {
            ConsoleUtils.printOption("2. Notificações (0)");
        }
        ConsoleUtils.printOption("3. Histórico de compras");
        ConsoleUtils.printOption("4. Propostas");
        if (user.isSupplier()) {
            ConsoleUtils.printOption("5. Histórico de vendas");
        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite uma opção válida.", 0, 1, 2, 3,
                4, 5);
        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                new InfoMenu(scanner, user);
                break;
            case 2:
                new UserNotification(scanner, user);
                break;
            case 3:
                new UserPurchases(scanner, user);
                break;
            case 4:
                if (!user.isSupplier()) {
                    new UserProposals(scanner, user);
                    return;
                }
                new ProposalMenu(scanner, user);
                break;
            case 5:
                if (user.isSupplier()) {
                    new UserSales(scanner, user);
                    break;
                }
            default:
                ConsoleUtils.printError("Opção inválida.");
                ConsoleUtils.timerConsole(2000);
                break;
        }
    }
}
