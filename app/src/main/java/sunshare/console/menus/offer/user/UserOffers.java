package sunshare.console.menus.offer.user;

import java.util.ArrayList;
import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.offer.Offer;
import sunshare.entities.offer.OfferStatus;
import sunshare.entities.user.User;
import sunshare.services.OfferService;

public class UserOffers {
    private boolean exit = false;

    public UserOffers(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        OfferService offerService = new OfferService();

        ArrayList<Offer> offers = offerService.getAllFromUser(user.getUuid());
        offers.removeIf(offer -> offer.getStatus() != OfferStatus.open);

        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Ofertas abertas de " + user.getName() + "--");
        ConsoleUtils.printExit("0. Voltar");
        if (offers.isEmpty()) {
            ConsoleUtils.printError("Nenhuma oferta encontrada.");
        } else {
            ConsoleUtils.printError("Escolha uma oferta que deseja deletar:\n");
            for (int i = 0; i < offers.size(); i++) {

                Offer offer = offers.get(i);

                ConsoleUtils.printSuccess(String.format("Oferta %d:", i + 1));
                String offerInfo = String.format(
                        "Produto: %s %s\n" +
                                "Valor pedido: R$ %.2f\n",
                        offer.getEnergy().getQuantity(),
                        offer.getEnergy().getSymbol(),
                        offer.getEnergy().getAmount());
                ConsoleUtils.printOption(offerInfo);
            }
        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ",
                "Digite 0 ou o número de uma oferta.", InputUtils.range(0, offers.size()));
        switch (option) {
            case 0:
                exit = true;
                break;
            default:
                if (option > 0 && option <= offers.size()) {
                    var offer = offers.get(option - 1);
                    ConsoleUtils.printSuccess("Oferta selecionada: " +
                            offer.getEnergy().getQuantity() + " " +
                            offer.getEnergy().getSymbol() + " - R$ " +
                            offer.getEnergy().getAmount());
                    ConsoleUtils.printOption("Tem certeza que deseja deletar essa oferta? (s/n)");
                    String confirmation = scanner.nextLine();
                    if (confirmation.equalsIgnoreCase("s")) {
                        if (!offer.getSupplierUuid().equals(user.getUuid())) {
                            ConsoleUtils.printError("Você não tem permissão para deletar essa oferta.");
                            ConsoleUtils.timerConsole(2000);
                            return;
                        }
                        offerService.delete(offer.getUuid());
                        ConsoleUtils.printSuccess("Oferta deletada com sucesso.");
                        ConsoleUtils.timerConsole(2000);
                        return;
                    }
                    ConsoleUtils.printError("Operação cancelada.");
                    ConsoleUtils.timerConsole(2000);
                } else {
                    ConsoleUtils.printError("Opção inválida.");
                    ConsoleUtils.timerConsole(2000);
                }
                break;
        }
    }
}
