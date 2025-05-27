package sunshare.console.menus.offer;

import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.energy.Energy;
import sunshare.entities.energy.EnergyMetricUnits;
import sunshare.entities.user.User;
import sunshare.services.OfferService;

public class CreateOfferMenu {
    private boolean exit = false;

    public CreateOfferMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    private double readPositiveDouble(Scanner scanner, String prompt, String errorMsg) {
        double value = -1;
        do {
            ConsoleUtils.printOption(prompt);
            String input = scanner.nextLine();
            try {
                value = Double.parseDouble(input);
                if (value <= 0) {
                    ConsoleUtils.printError(errorMsg);
                }
            } catch (NumberFormatException e) {
                ConsoleUtils.printError("Digite apenas números válidos.");
                value = -1;
            }
        } while (value <= 0);
        return value;
    }

    private int readZeroOrOne(Scanner scanner, String prompt, String errorMsg) {
        int value;
        do {
            ConsoleUtils.printOption(prompt);
            while (!scanner.hasNextInt()) {
                ConsoleUtils.printError(errorMsg);
                scanner.next();
            }
            value = scanner.nextInt();
            if (value != 0 && value != 1) {
                ConsoleUtils.printError(errorMsg);
            }
        } while (value != 0 && value != 1);
        scanner.nextLine();
        return value;
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Criar Oferta");
        ConsoleUtils.printOption("Deseja criar uma oferta? (s/n)");
        String option = scanner.nextLine();
        if (!option.equalsIgnoreCase("s")) {
            ConsoleUtils.printError("Operação cancelada.");
            ConsoleUtils.timerConsole(2000);
            exit = true;
            return;
        }
        ConsoleUtils.clearConsole();
        int metricOption = readZeroOrOne(scanner, "Escolha a unidade de medida: 0 - kW ou 1 - MW",
                "Digite 0 para kW ou 1 para MW.");
        double quantity = readPositiveDouble(scanner, "Digite a quantidade de energia: ",
                "Quantidade deve ser um número maior que zero.");
        double amount = readPositiveDouble(scanner, "Digite o valor da oferta: ",
                "Valor deve ser um número maior que zero.");
        EnergyMetricUnits metric = EnergyMetricUnits.values()[metricOption];

        Energy energy = new Energy(
                metric.symbol,
                quantity,
                amount);

        OfferService offerService = new OfferService();
        offerService.create(user.getUuid(), energy);
        ConsoleUtils.printSuccess("Oferta criada com sucesso!");
        ConsoleUtils.timerConsole(2000);
        exit = true;
    }
}
