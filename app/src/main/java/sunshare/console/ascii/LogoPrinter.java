package sunshare.console.ascii;

import java.io.InputStream;

public class LogoPrinter extends AbstractAsciiPrinter {
    /*
     * Sempre que for trabalhar com resources
     * colocar a barra antes do nome do arquivo
     * evita muita dor de cabeça.
     * Precisa disso porque o arquivo fica no root
     * do classpath do java.
     */
    private static final String logoPath = "/logo.txt";

    @Override
    protected InputStream getResourceInputStream() {
        final InputStream inputStream = getClass().getResourceAsStream(logoPath);
        return inputStream;
    }
}
