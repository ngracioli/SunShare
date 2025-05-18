package sunshare.console.logo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import sunshare.console.menus.utils.ConsoleUtils;

public class Logo {
    final private String logoPath = "app/src/main/resources/logo.txt";

    public Logo() {
        try {
            showMenu();
        } catch (IOException e) {
            return;
        }
    }

    public void showMenu() throws IOException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        fileReader = new FileReader(logoPath);
        bufferedReader = new BufferedReader(fileReader);
        readAndPrintBuffer(bufferedReader);

        if (fileReader != null) {
            fileReader.close();
        }

        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }

    private void readAndPrintBuffer(BufferedReader br) throws IOException {
        ConsoleUtils.clearConsole();

        String line;
        while ((line = br.readLine()) != null) {
            ConsoleUtils.printTitle(line);
        }
    }
}
