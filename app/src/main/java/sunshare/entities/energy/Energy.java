package sunshare.entities.energy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Energy {
    private String symbol;
    private double amount;

    @JsonCreator
    Energy(
            @JsonProperty("symbol") String symbol,
            @JsonProperty("amount") double amount) {
        this.amount = amount;
        this.symbol = symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}