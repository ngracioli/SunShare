package sunshare.console.menus.profile.proposals;

import java.util.Scanner;
import sunshare.console.menus.profile.proposals.user.*;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.user.User;

public class ProposalMenu {
    private boolean exit = false;

    public ProposalMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Propostas");
        ConsoleUtils.printExit("0. Voltar");
        ConsoleUtils.printOption("1. Minhas propostas");
        if (user.isSupplier()) {
            ConsoleUtils.printOption("2. Propostas recebidas");
        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite uma opção válida.", 0, 1, 2);
        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                new UserProposals(scanner, user);
                break;
            case 2:
                if (user.isSupplier()) {
                    new ReceivedProposals(scanner, user);
                    break;
                }
            default:
                ConsoleUtils.printError("Opção inválida.");
                ConsoleUtils.timerConsole(2000);
                break;
        }
    }
}
