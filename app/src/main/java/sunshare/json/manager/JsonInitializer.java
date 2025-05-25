package sunshare.json.manager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonInitializer {
    public static Path databasePath = Path.of("database");

    public static void initialize() {
        try {
            createDatabaseDirectory();
            createJsonFiles();
        } catch (IOException e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createDatabaseDirectory() throws IOException {
        if (!Files.exists(databasePath)) {
            Files.createDirectories(databasePath);
        }
    }

    private static void createJsonFiles() throws IOException {
        final JsonFiles files[] = JsonFiles.values();
        for (JsonFiles file : files) {
            Path filePath = file.getFullPath();

            if (!Files.exists(filePath)) {
                Files.writeString(filePath, "[]");
            }
        }
    }

}