package sunshare.console.ascii;

public class LogoPrinter extends AbstractAsciiPrinter {
    final private String logoPath = "app/src/main/resources/logo.txt";

    @Override
    protected String getFilePath() {
        return logoPath;
    }

}
