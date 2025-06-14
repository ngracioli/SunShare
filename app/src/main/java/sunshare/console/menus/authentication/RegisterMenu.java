package sunshare.console.menus.authentication;

import java.util.Scanner;
import sunshare.console.menus.main.MainMenu;
import sunshare.console.menus.utils.ConsoleUtils;
import sunshare.console.menus.utils.InputUtils;
import sunshare.entities.address.Address;
import sunshare.entities.document.Document;
import sunshare.entities.document.DocumentTypes;
import sunshare.entities.user.User;
import sunshare.services.AuthService;

public class RegisterMenu {
    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidCep(String cep) {
        return cep.matches("^\\d{5}-?\\d{3}$");
    }

    private boolean isValidCPF(String cpf) {
        return cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$");
    }

    private boolean isValidCNPJ(String cnpj) {
        return cnpj.matches("^\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}$|^\\d{14}$");
    }

    private String formatCep(String cep) {
        String digits = cep.replaceAll("\\D", "");
        if (digits.length() == 8) {
            return digits.substring(0, 5) + "-" + digits.substring(5);
        }
        return cep;
    }

    private String formatCpf(String cpf) {
        String digits = cpf.replaceAll("\\D", "");
        if (digits.length() == 11) {
            return digits.substring(0, 3) + "." + digits.substring(3, 6) + "." + digits.substring(6, 9) + "-"
                    + digits.substring(9);
        }
        return cpf;
    }

    private String formatCnpj(String cnpj) {
        String digits = cnpj.replaceAll("\\D", "");
        if (digits.length() == 14) {
            return digits.substring(0, 2) + "." + digits.substring(2, 5) + "." + digits.substring(5, 8) + "/"
                    + digits.substring(8, 12) + "-" + digits.substring(12);
        }
        return cnpj;
    }

    public RegisterMenu(Scanner scanner, AuthService authService) {
        ConsoleUtils.clearConsole();
        ConsoleUtils.printTitle("-- Cadastro de Usuário --");

        String name = readNonEmptyString(scanner, "Digite o nome: ", "Nome não pode ser vazio.");
        String email;
        do {
            ConsoleUtils.printOption("Digite o e-mail: ");
            email = scanner.nextLine();
            if (!isValidEmail(email) || authService.getUserByEmail(email) != null) {
                ConsoleUtils.printError("E-mail inválido ou já existente.");
            }
        } while (!isValidEmail(email) || authService.getUserByEmail(email) != null);
        String password = readNonEmptyString(scanner, "Digite a senha: ", "Senha não pode ser vazia.");
        Address address = readAddress(scanner);
        Document document = readDocument(scanner);
        ConsoleUtils.printOption("(0) Comprador ou (1) Vendedor? : ");

        int userType = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite 0 ou 1.", 0, 1);
        final User user;

        if (userType == 1) {
            user = authService.registerSupplier(name, email, password, address, document);
        } else {
            user = authService.registerBuyer(name, email, password, address, document);
        }

        ConsoleUtils.printSuccess("Cadastro realizado com sucesso!");
        ConsoleUtils.timerConsole(2000);
        new MainMenu(scanner, user);
    }

    private String readNonEmptyString(Scanner scanner, String prompt, String errorMsg) {
        String value;
        do {
            ConsoleUtils.printOption(prompt);
            value = scanner.nextLine();
            if (value.isBlank())
                ConsoleUtils.printError(errorMsg);
        } while (value.isBlank());
        return value;
    }

    private Address readAddress(Scanner scanner) {
        String state = readNonEmptyString(scanner, "Digite o estado: ", "Estado não pode ser vazio.").toUpperCase();
        String city = readNonEmptyString(scanner, "Digite a cidade: ", "Cidade não pode ser vazia.").toUpperCase();
        String neighborhood = readNonEmptyString(scanner, "Digite o bairro: ", "Bairro não pode ser vazio.")
                .toUpperCase();
        String street = readNonEmptyString(scanner, "Digite a rua: ", "Rua não pode ser vazia.").toUpperCase();
        String cep;
        do {
            ConsoleUtils.printOption("Digite o CEP: ");
            cep = scanner.nextLine();
            cep = formatCep(cep);
            if (!isValidCep(cep)) {
                ConsoleUtils.printError("CEP inválido. Use o formato 00000-000.");
            }
        } while (!isValidCep(cep));
        return new Address(state, city, neighborhood, street, cep);
    }

    private Document readDocument(Scanner scanner) {
        ConsoleUtils.printOption("(0) CPF ou (1) CNPJ? : ");
        int option = InputUtils.readIntOption(scanner, "Escolha uma opção: ", "Digite 0 ou 1.", 0, 1);

        final DocumentTypes documentType = DocumentTypes.values()[option];
        String document;

        do {
            ConsoleUtils.printOption("Digite seu " + documentType.type + ": ");
            document = scanner.nextLine();

            if (option == 0) {
                document = formatCpf(document);
                if (!isValidCPF(document)) {
                    ConsoleUtils.printError("CPF inválido. Use 000.000.000-00 ou apenas números.");
                }
            } else {
                document = formatCnpj(document);
                if (!isValidCNPJ(document)) {
                    ConsoleUtils.printError("CNPJ inválido. Use 00.000.000/0000-00 ou apenas números.");
                }
            }
        } while ((option == 0 && !isValidCPF(document)) || (option == 1 && !isValidCNPJ(document)));

        return new Document(documentType, document);
    }
}
