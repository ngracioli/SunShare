package sunshare.entities.ofert;

import sunshare.entities.energy.Energy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Offer {
    private String uuid;
    private String supplierUuid;
    private Energy energy;
    private OfertStatus status;

    @JsonCreator
    public Offer(
            @JsonProperty("uuid") String uuid,
            @JsonProperty("supplier_uuid") String supplierUuid,
            @JsonProperty("energy") Energy energy,
            @JsonProperty("status") OfertStatus status) {
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

    public OfertStatus getStatus() {
        return status;
    }

    public void setStatus(OfertStatus status) {
        this.status = status;
    }

}