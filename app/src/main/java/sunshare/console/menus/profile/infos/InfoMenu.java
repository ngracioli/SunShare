package sunshare.console.menus.profile.infos;

import java.util.Scanner;

import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.address.Address;
import sunshare.entities.document.Document;
import sunshare.entities.user.User;

public class InfoMenu {

    public InfoMenu(Scanner scanner, User user) {
        final Address address = user.getAddress();
        final Document document = user.getDocument();
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Minhas informações");
        ConsoleUtils.printSuccess("Nome: " + user.getName());
        ConsoleUtils.printSuccess("Email: " + user.getEmail());
        ConsoleUtils.printSuccess("Tipo: " + (user.isSupplier() ? "Fornecedor" : "Consumidor"));
        ConsoleUtils.printSuccess("Compras:" + user.getTotalPurchases());
        ConsoleUtils.printSuccess("Vendas: " + user.getTotalSales());
        ConsoleUtils.printSuccess("Endereço: " + address.getStreet() + ", " + address.getNeighborhood() + ", "
                + address.getCity() + ", " + address.getState() + ", " + address.getCep());
        ConsoleUtils.printSuccess("Tipo de documento: " + document.getDocumentType().type);
        ConsoleUtils.printSuccess("Número do documento: " + document.getDocument());
        ConsoleUtils.printError("Pressione ENTER para voltar");
        scanner.nextLine();
        return;
    }
}
