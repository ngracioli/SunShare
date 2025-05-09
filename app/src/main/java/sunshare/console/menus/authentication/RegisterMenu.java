package sunshare.console.menus.authentication;

import java.util.Scanner;
import sunshare.console.menus.main.MainMenu;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.entities.address.Address;
import sunshare.entities.document.Document;
import sunshare.entities.document.DocumentTypes;
import sunshare.entities.user.User;
import sunshare.services.AuthService;

public class RegisterMenu {
    public static void show(Scanner scanner, AuthService authService) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("Cadastro de Usuário");
        ConsoleUtils.printOption("Digite o nome: ");
        String name = scanner.nextLine();
        ConsoleUtils.printOption("Digite o e-mail: ");
        String email = scanner.nextLine();
        ConsoleUtils.printOption("Digite a senha: ");
        String password = scanner.nextLine();
        Address address = readAddress(scanner);
        Document document = readDocument(scanner);
        ConsoleUtils.printOption("(0) Comprador ou (1) Vendedor? : ");

        int userType = scanner.nextInt();
        final User user;

        while (userType != 0 && userType != 1) {
            ConsoleUtils.printError("Opção inválida. Tente novamente.");
            userType = scanner.nextInt();
        }

        if (userType == 1) {
            user = authService.registerSupplier(name, email, password, address, document);
        } else {
            user = authService.registerBuyer(name, email, password, address, document);
        }

        ConsoleUtils.printSuccess("Cadastro realizado com sucesso!");
        ConsoleUtils.timerConsole(2000);
        MainMenu.show(scanner, user);
    }

    private static Address readAddress(Scanner scanner) {
        ConsoleUtils.printOption("Digite o estado: ");
        String state = scanner.nextLine();
        ConsoleUtils.printOption("Digite a cidade: ");
        String city = scanner.nextLine();
        ConsoleUtils.printOption("Digite o bairro: ");
        String neighborhood = scanner.nextLine();
        ConsoleUtils.printOption("Digite a rua: ");
        String street = scanner.nextLine();
        ConsoleUtils.printOption("Digite o CEP: ");
        String cep = scanner.nextLine();

        return new Address(state, city, neighborhood, street, cep);
    }

    private static Document readDocument(Scanner scanner) {
        ConsoleUtils.printOption("(0) CPF ou (1) CNPJ? : ");
        int option = scanner.nextInt();

        while (option != 0 && option != 1) {
            ConsoleUtils.printError("Opção inválida. Tente novamente.");
            option = scanner.nextInt();
        }

        // Clear the newline character left by nextInt()
        scanner.nextLine();

        final DocumentTypes documentType = DocumentTypes.values()[option];

        ConsoleUtils.printOption("Digite seu " + documentType.type + ": ");
        String document = scanner.nextLine();

        return new Document(documentType, document);
    }
}
