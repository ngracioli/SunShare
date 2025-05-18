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

    /**
     * Exite a arte ascii dentro do arquivo
     *
     * @throws Exception
     */
    protected void print() throws Exception {
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
        Consumer<String> printer = getPrinter();
        while ((line = br.readLine()) != null) {
            printer.accept(line);
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
    protected Consumer<String> getPrinter() {
        return ConsoleUtils::printTitle;
    }

}