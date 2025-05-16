package sunshare.console.menus.offer;

import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.user.User;
import sunshare.services.OfferService;

public class OfferMenu {
    private boolean exit = false;

    public OfferMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printSuccess("-- Ofertas --");
        ConsoleUtils.printExit("0. Voltar");

        OfferService offerService = new OfferService();
        var offers = offerService.getAll();

        if (offers.isEmpty()) {
            ConsoleUtils.printError("Nenhuma oferta disponível.");
        } else {
            for (int i = 0; i < offers.size(); i++) {
                var offer = offers.get(i);
                ConsoleUtils.printOption((i + 1) + ". " +
                        offer.getEnergy().getQuantity() + " " +
                        offer.getEnergy().getSymbol() + " - R$ " +
                        offer.getEnergy().getAmount());
            }
        }
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite 0 ou o número de uma oferta.",
                range(0, offers.size()));
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
                    ConsoleUtils.timerConsole(2000);
                } else {
                    ConsoleUtils.printError("Opção inválida.");
                    ConsoleUtils.timerConsole(2000);
                }
                break;
        }
    }

    // Função utilitária para criar um array de inteiros de 0 até n
    private int[] range(int start, int end) {
        int[] arr = new int[end - start + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = start + i;
        }
        return arr;
    }
}
