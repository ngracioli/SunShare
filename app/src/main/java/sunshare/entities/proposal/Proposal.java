package sunshare.entities.proposal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Proposal {

    private String buyerUuid;
    private String supplierUuid;
    private String offerUuid;

    @JsonCreator
    public Proposal(
        @JsonProperty("buyerUuid") String buyerUuid,
        @JsonProperty("supplierUuid") String supplierUuid,
        @JsonProperty("offerUuid") String offerUuid
    ) {
        this.buyerUuid = buyerUuid;
        this.supplierUuid = supplierUuid;
        this.offerUuid = offerUuid;
    }

    public void setBuyerUuid(String buyerUuid) {
        this.buyerUuid = buyerUuid;
    }

    public String getBuyerUuid() {
        return buyerUuid;
    }

    public void setSupplierUuid(String supplierUuid) {
        this.supplierUuid = supplierUuid;
    }

    public String getSupplierUuid() {
        return supplierUuid;
    }

    public void setOfferUuid(String offerUuid) {
        this.offerUuid = offerUuid;
    }

    public String getOfferUuid() {
        return offerUuid;
    }
}