package sunshare.entities.offer;

import sunshare.entities.energy.Energy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Offer {
    private String uuid;
    private String supplierUuid;
    private String buyerUuid;
    private Energy energy;
    private OfferStatus status;
    private double acceptedValue;

    @JsonCreator
    public Offer(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("supplier_uuid") String supplierUuid,
            @JsonProperty("buyer_uuid") String buyerUuid,
            @JsonProperty("energy") Energy energy,
            @JsonProperty("status") OfferStatus status,
            @JsonProperty("acceptedValue") double acceptedValue) {
        this.uuid = uuid;
        this.supplierUuid = supplierUuid;
        this.buyerUuid = buyerUuid;
        this.energy = energy;
        this.status = status;
        this.acceptedValue = acceptedValue;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSupplierUuid() {
        return supplierUuid;
    }

    public void setSupplierUuid(String supplierUuid) {
        this.supplierUuid = supplierUuid;
    }

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public String getBuyerUuid() {
        return buyerUuid;
    }

    public void setBuyerUuid(String buyerUuid) {
        this.buyerUuid = buyerUuid;
    }

    public double getAcceptedValue() {
        return acceptedValue;
    }

    public void setAcceptedValue(double acceptedValue) {
        this.acceptedValue = acceptedValue;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "uuid='" + uuid + '\'' +
                ", supplierUuid='" + supplierUuid + '\'' +
                ", buyerUuid='" + buyerUuid + '\'' +
                ", energy=" + energy +
                ", status=" + status +
                ", acceptedValue=" + acceptedValue +
                '}';
    }
}