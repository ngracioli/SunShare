package sunshare.app;

import java.util.Scanner;

import org.fusesource.jansi.internal.JansiLoader;

import sunshare.entities.Address;
import sunshare.entities.Document;
import sunshare.entities.User;
import sunshare.services.AuthService;

public class App {
    private static void timerConsole(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erro no timer");
        }
    }

    private static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar o console");
        }
    }

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        Scanner scanner = new Scanner(System.in);

        boolean appRunning = true;

        while (appRunning) {
            clearConsole();
            System.out.println("=== SunShare ===");
            System.out.println("1. Registrar");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            clearConsole();

            switch (choice) {
                case 1:
                    System.out.println("=== Registrar ===");
                    System.out.print("Digite o nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String password = scanner.nextLine();

                    Address address = readAddress(scanner);
                    Document document = readDocument(scanner);

                    authService.register(name, email, password, address, document);
                    System.out.println("Registro realizado com sucesso!");
                    timerConsole(2000);
                    break;

                case 2:
                    System.out.println("=== Login ===");
                    System.out.print("Digite o email: ");
                    String loginEmail = scanner.nextLine();
                    System.out.print("Digite a senha: ");
                    String loginPassword = scanner.nextLine();
                    User user = authService.login(loginEmail, loginPassword);
                    if (user != null) {
                        System.out.println("Login realizado com sucesso! Bem-vindo, " +
                                user.getName());
                        System.out.println("Seu endereço é: ");
                        System.out.println(user.getAddress().formatAdress());
                        System.out.println("Saindo do sistema...");

                        return;
                    } else {
                        System.out.println("Email ou senha inválidos.");
                        timerConsole(2000);
                    }
                    break;

                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    appRunning = false;
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    timerConsole(2000);
            }
        }
    }

    public static Address readAddress(Scanner scanner) {
        System.out.print("Digite o estado: ");
        String state = scanner.nextLine();
        System.out.print("Digite a cidade: ");
        String city = scanner.nextLine();
        System.out.print("Digite o bairro: ");
        String neighborhood = scanner.nextLine();
        System.out.print("Digite a rua: ");
        String street = scanner.nextLine();
        System.out.print("Digite o CEP: ");
        String cep = scanner.nextLine();

        return new Address(state, city, neighborhood, street, cep);
    }

    public static Document readDocument(Scanner scanner) {
        System.out.print("Digite o tipo de documento: ");
        String documentType = scanner.nextLine();
        System.out.print("Digite o documento: ");
        String document = scanner.nextLine();

        return new Document(documentType, document);

    }
}
