package sunshare.console.menus.profile;

import java.util.Scanner;

import sunshare.console.menus.offer.OfferMenu;
import sunshare.console.menus.profile.infos.InfoMenu;
import sunshare.console.menus.profile.purchases.UserPurchases;
import sunshare.console.menus.profile.sales.UserSales;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.console.menus.profile.proposals.ProposalMenu;
import sunshare.console.menus.profile.proposals.user.UserProposals;
import sunshare.entities.user.User;

public class ProfileMenu {
    private boolean exit = false;

    public ProfileMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Perfil");
        ConsoleUtils.printExit("0. Voltar");
        ConsoleUtils.printOption("1. Minha informações");
        ConsoleUtils.printOption("2. Notificações");
        ConsoleUtils.printOption("3. Histórico de compras");
        ConsoleUtils.printOption("4. Propostas");
        if (user.isSupplier()) {
            ConsoleUtils.printOption("5. Vendas efetuadas");
        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite uma opção válida.", 0, 1, 2, 3,
                4,5);
        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                new InfoMenu(scanner, user);
                break;
            case 2:
                //notificações menu
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
