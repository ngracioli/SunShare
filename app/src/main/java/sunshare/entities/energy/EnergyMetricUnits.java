package sunshare.entities.energy;

public enum EnergyMetricUnits {
    kilowatts("kW"),
    megawatts("MW");

    EnergyMetricUnits(String symbol) {
        this.symbol = symbol;
    }

    public final String symbol;
}