package sunshare.json.manager;

import java.io.File;
import java.nio.file.Path;

public enum JsonFiles {
    users("users.json"),
    offers("offers.json"),
    proposals("proposals.json"),
    notifications("notifications.json");

    private final String fileName;
    private Path fullPath;

   JsonFiles(String fileName) {
        this.fileName = fileName;
        this.fullPath = JsonInitializer.databasePath.resolve(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public Path getFullPath() {
        return JsonInitializer.databasePath.resolve(fileName);
    }

    public File getFile() {
        return getFullPath().toFile();
    }
}