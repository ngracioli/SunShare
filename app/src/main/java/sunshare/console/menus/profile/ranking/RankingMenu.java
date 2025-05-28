package sunshare.console.menus.profile.ranking;

import java.util.Scanner;

import sunshare.console.menus.profile.proposals.user.ReceivedProposals;
import sunshare.console.menus.profile.proposals.user.UserProposals;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.offer.Offer;
import sunshare.entities.proposal.Proposal;
import sunshare.entities.user.User;

public class RankingMenu {
    private boolean exit = false;

    public RankingMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Menu de Ranking --");
        ConsoleUtils.printExit("0. Voltar");
        ConsoleUtils.printOption("1. Ranking de Fornecedores");
        ConsoleUtils.printOption("2. Ranking de Compradores");
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite uma opção válida.", 0, 1, 2);
        switch (option) {
            case 0:
                exit = true;
                break;
            case 1:
                new UserRanking(scanner, user, true);
                break;
            case 2:
                new UserRanking(scanner, user, false);
                break;
            default:
                ConsoleUtils.printError("Opção inválida.");
                ConsoleUtils.timerConsole(2000);
                break;
        }
    }
}
