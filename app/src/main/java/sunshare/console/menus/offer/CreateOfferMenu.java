package sunshare.console.menus.offer;

import java.util.Scanner;

import sunshare.console.menus.profile.ProfileMenu;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.energy.Energy;
import sunshare.entities.energy.EnergyMetricUnits;
import sunshare.entities.offer.Offer;
import sunshare.entities.user.User;
import sunshare.services.OfferService;

public class CreateOfferMenu {
    private boolean exit = false;

    public CreateOfferMenu(Scanner scanner, User user) {
        while (!exit) {
            showMenu(scanner, user);
        }
    }

    public void showMenu(Scanner scanner, User user) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Criar Oferta");
        ConsoleUtils.printOption("Escolha a unidade de medida: 0 - kW ou 1 - MW");
        int metricOption = scanner.nextInt();
        ConsoleUtils.printOption("Digite a quantidade de energia: ");
        double quantity = scanner.nextDouble();
        ConsoleUtils.printOption("Digite o valor da oferta: ");
        double amount = scanner.nextDouble();

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
