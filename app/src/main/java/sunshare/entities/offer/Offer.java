package sunshare.entities.offer;

import sunshare.entities.energy.Energy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Offer {
    private String uuid;
    private String supplierUuid;
    private Energy energy;
    private OfferStatus status;

    @JsonCreator
    public Offer(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("supplier_uuid") String supplierUuid,
            @JsonProperty("energy") Energy energy,
            @JsonProperty("status") OfferStatus status) {
        this.uuid = uuid;
        this.supplierUuid = supplierUuid;
        this.energy = energy;
        this.status = status;
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

    @Override
    public String toString() {
        return "Offer [uuid=" + uuid + ", supplierUuid=" + supplierUuid + ", energy=" + energy + ", status=" + status
                + "]";
    }
}