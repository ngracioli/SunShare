package sunshare.entities.energy;

public enum EnergyMetricUnits {
    kilowatts("kW"),
    megawatts("MW");

    EnergyMetricUnits(String symbol) {
        this.symbol = symbol;
    }

    private final String symbol;
}