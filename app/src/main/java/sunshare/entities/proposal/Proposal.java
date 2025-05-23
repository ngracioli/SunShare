package sunshare.entities.proposal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Proposal {

    private String proposalUuid;
    private String buyerUuid;
    private String supplierUuid;
    private String offerUuid;
    private double proposalValue;
    private ProposalStatus status;

    @JsonCreator
    public Proposal(
            @JsonProperty("proposalUuid") String proposalUuid,
            @JsonProperty("buyerUuid") String buyerUuid,
            @JsonProperty("supplierUuid") String supplierUuid,
            @JsonProperty("offerUuid") String offerUuid,
            @JsonProperty("proposalValue") double proposalValue,
            @JsonProperty("status") ProposalStatus status) {
        this.proposalUuid = proposalUuid;
        this.buyerUuid = buyerUuid;
        this.supplierUuid = supplierUuid;
        this.offerUuid = offerUuid;
        this.proposalValue = proposalValue;
        this.status = status;
    }

    public String getProposalUuid() {
        return proposalUuid;
    }

    public void setProposalUuid(String proposalUuid) {
        this.proposalUuid = proposalUuid;
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

    public double getProposalValue() {
        return proposalValue;
    }

    public void setProposalValue(double proposalValue) {
        this.proposalValue = proposalValue;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "proposalUuid='" + proposalUuid + '\'' +
                ", buyerUuid='" + buyerUuid + '\'' +
                ", supplierUuid='" + supplierUuid + '\'' +
                ", offerUuid='" + offerUuid + '\'' +
                ", proposalValue=" + proposalValue +
                ", status=" + status +
                '}';
    }
}