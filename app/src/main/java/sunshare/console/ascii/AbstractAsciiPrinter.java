package sunshare.console.ascii;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

import sunshare.console.menus.utils.ConsoleUtils;

/**
 * Classe abstrata para imprimir artes ASCII
 * de arquivos
 */
public abstract class AbstractAsciiPrinter {
    public AbstractAsciiPrinter() {
        try {
            print();
        } catch (Exception e) {
            /**
             * Retorno direto porque o propósito da classe é printar
             * uma arte ASCII, e se não conseguir, não faz sentido
             * continuar. Para os incomodados, queimem, estou com dor
             * de cabeça e não quero mais ver isso.
             */
            return;
        }
    }

    private void print() throws Exception {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        fileReader = new FileReader(getFilePath());
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
        String line;
        int row = 0;
        Consumer<String> printer;
        while ((line = br.readLine()) != null) {
            printer = getPrinter(row);
            printer.accept(line);
            row++;
        }
    }

    /**
     * Retorna o caminho do arquivo que contém a arte ascii
     *
     * @return
     */
    protected abstract String getFilePath();

    /**
     * Retorna o Consumer que vai imprimir a arte ascii
     *
     * @return
     */
    protected Consumer<String> getPrinter(int row) {
        return ConsoleUtils::printTitle;
    }

}