package sunshare.entities.energy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Energy {
    private String symbol;
    private double amount;
    private double quantity;

    @JsonCreator
    public Energy(
            @JsonProperty("symbol") String symbol,
            @JsonProperty("quantity") double quantity,
            @JsonProperty("amount") double amount) {
        this.amount = amount;
        this.quantity = quantity;
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

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Energy [symbol=" + symbol + ", amount=" + amount + ", quantity=" + quantity + "]";
    }

}