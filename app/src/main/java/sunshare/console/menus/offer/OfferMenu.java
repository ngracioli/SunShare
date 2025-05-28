package sunshare.console.menus.offer;

import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.offer.OfferStatus;
import sunshare.entities.user.User;
import sunshare.services.OfferService;
import sunshare.services.ProposalService;
import sunshare.services.UserService;

public class OfferMenu {
    private boolean exit = false;

    public OfferMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Ofertas --");
        ConsoleUtils.printExit("0. Voltar");

        OfferService offerService = new OfferService();
        UserService userService = new UserService();
        var offers = offerService.getAll(OfferStatus.open);
        var filteredOffers = offers.stream()
                .filter(offer -> !offer.getSupplierUuid().equals(user.getUuid()))
                .toList();

        if (filteredOffers.isEmpty()) {
            ConsoleUtils.printError("Nenhuma oferta disponível.");
        } else {
            for (int i = 0; i < filteredOffers.size(); i++) {
                var offer = filteredOffers.get(i);
                var supplier = userService.getByUuid(offer.getSupplierUuid());
                String offerInfo = String.format(
                        "%d. %s %s - R$%.2f - Vendedor: %s",
                        i + 1,
                        offer.getEnergy().getQuantity(),
                        offer.getEnergy().getSymbol(),
                        offer.getEnergy().getAmount(),
                        supplier.getName());
                ConsoleUtils.printOption(offerInfo);
            }
        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ",
                "Digite 0 ou o número de uma oferta.", InputUtils.range(0, filteredOffers.size()));
        switch (option) {
            case 0:
                exit = true;
                break;
            default:
                if (option > 0 && option <= filteredOffers.size()) {
                    var offer = filteredOffers.get(option - 1);
                    ConsoleUtils.printSuccess("Oferta selecionada: " +
                            offer.getEnergy().getQuantity() + " " +
                            offer.getEnergy().getSymbol() + " - R$ " +
                            offer.getEnergy().getAmount());
                    ConsoleUtils.printOption("Deseja fazer uma proposta? s/n");
                    String makeProposal = scanner.nextLine();
                    if (!makeProposal.equalsIgnoreCase("s")) {
                        ConsoleUtils.printError("Operação cancelada.");
                        ConsoleUtils.timerConsole(2000);
                        return;
                    }
                    ConsoleUtils.clearConsole();
                    double minValue = offer.getEnergy().getAmount() * 0.75;
                    ConsoleUtils.printOption("Digite o valor da proposta: ");
                    ConsoleUtils
                            .printSuccess("Valor original: R$ " + String.format("%.2f", offer.getEnergy().getAmount()));
                    ConsoleUtils.printSuccess("Valor mínimo: R$ " + String.format("%.2f", minValue));
                    double proposalValue = -1;
                    do {
                        String input = scanner.nextLine();
                        try {
                            proposalValue = Double.parseDouble(input);
                            if (proposalValue < minValue) {
                                ConsoleUtils.printError("Valor da proposta abaixo do mínimo.");
                                ConsoleUtils.timerConsole(2000);
                                proposalValue = -1;
                            }
                        } catch (NumberFormatException e) {
                            ConsoleUtils.printError("Digite apenas números válidos.");
                            proposalValue = -1;
                        }
                    } while (proposalValue < minValue);
                    ProposalService proposalService = new ProposalService();
                    var proposal = proposalService.create(user.getUuid(), offer.getSupplierUuid(), offer.getUuid(),
                            proposalValue);
                    if (proposal != null) {
                        ConsoleUtils.printSuccess("Proposta criada com sucesso!");
                        ConsoleUtils.timerConsole(2000);
                    } else {
                        ConsoleUtils.printError("Erro ao criar proposta.");
                        ConsoleUtils.timerConsole(2000);
                    }
                } else {
                    ConsoleUtils.printError("Opção inválida.");
                    ConsoleUtils.timerConsole(2000);
                }
                break;
        }
    }
}
