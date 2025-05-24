package sunshare.console.menus.profile.proposals.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.offer.Offer;
import sunshare.entities.proposal.Proposal;
import sunshare.entities.proposal.ProposalStatus;
import sunshare.entities.user.User;
import sunshare.services.OfferService;
import sunshare.services.ProposalService;
import sunshare.services.UserService;

public class ReceivedProposals {
    private boolean exit = false;

    public ReceivedProposals(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ProposalService proposalService = new ProposalService();
        OfferService offerService = new OfferService();
        UserService userService = new UserService();
        ArrayList<Proposal> proposals = proposalService.getAllBySupplierUuid(user.getUuid());

        proposals.removeIf(proposal -> proposal.getStatus() != ProposalStatus.open);

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Propostas Recebidas --");
        ConsoleUtils.printExit("0. Voltar\n");
        if (proposals.isEmpty()) {
            ConsoleUtils.printError("Nenhuma proposta encontrada.");
        } else {
            for (int i = 0; i < proposals.size(); i++) {
                Proposal proposal = proposals.get(i);
                Offer offer = offerService.getByUuid(proposal.getOfferUuid());
                User buyer = userService.getByUuid(proposal.getBuyerUuid());
                ConsoleUtils.printSuccess(String.format("%d. Proposta", i + 1));
                String proposalInfo = String.format(
                        "Comprador: %s\n" +
                                "Produto: %s %s\n" +
                                "Valor pedido: R$ %.2f\n" +
                                "Valor oferecido: R$ %.2f\n",
                        buyer.getName(),
                        offer.getEnergy().getQuantity(),
                        offer.getEnergy().getSymbol(),
                        offer.getEnergy().getAmount(),
                        proposal.getProposalValue());
                ConsoleUtils.printOption(proposalInfo);
            }

        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite 0 ou o número de uma proposta.",
                InputUtils.range(0, proposals.size()));
        switch (option) {
            case 0:
                exit = true;
                break;
            default:
                if (option > 0 && option <= proposals.size()) {
                    var proposal = proposals.get(option - 1);
                    ConsoleUtils.printSuccess("Proposta selecionada!");
                    ConsoleUtils.printOption("Deseja aceitar ou recusar a proposta? (aceitar/recusar)");
                    String response = scanner.nextLine();
                    if (response.equalsIgnoreCase("aceitar")) {
                        ConsoleUtils.printSuccess("Tem certeza que deseja aceitar a proposta? (s/n)");
                        String confirm = scanner.nextLine();
                        if (!confirm.equalsIgnoreCase("s")) {
                            ConsoleUtils.printError("Operação cancelada.");
                            ConsoleUtils.timerConsole(2000);
                            return;
                        }
                        proposalService.accept(proposal.getProposalUuid());
                        ConsoleUtils.printSuccess("Proposta aceita com sucesso!");
                        ConsoleUtils.timerConsole(2000);
                    } else if (response.equalsIgnoreCase("recusar")) {
                        ConsoleUtils.printError("Proposta rejeitada com sucesso!");
                        ConsoleUtils.timerConsole(2000);
                    } else {
                        ConsoleUtils.printError("Opção inválida.");
                        ConsoleUtils.timerConsole(2000);
                    }
                }
                break;
        }
    }
}
