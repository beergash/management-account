package it.beergash.management.account.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TaxRelief {

    private String taxReliefId;
    private boolean isCondoUpgrade;
    @NotBlank(message = "creditorFiscalCode must not be null")
    private String creditorFiscalCode;
    @Valid
    @NotNull(message = "naturalPersonBeneficiary must not be null")
    private NaturalBeneficiary naturalPersonBeneficiary;
    @Valid
    @NotNull(message = "legalPersonBeneficiary must not be null")
    private LegalBeneficiary legalPersonBeneficiary;

    public String getTaxReliefId() {
        return taxReliefId;
    }

    public void setTaxReliefId(String taxReliefId) {
        this.taxReliefId = taxReliefId;
    }

    public boolean isCondoUpgrade() {
        return isCondoUpgrade;
    }

    public void setCondoUpgrade(boolean condoUpgrade) {
        isCondoUpgrade = condoUpgrade;
    }

    public String getCreditorFiscalCode() {
        return creditorFiscalCode;
    }

    public void setCreditorFiscalCode(String creditorFiscalCode) {
        this.creditorFiscalCode = creditorFiscalCode;
    }

    public NaturalBeneficiary getNaturalPersonBeneficiary() {
        return naturalPersonBeneficiary;
    }

    public void setNaturalPersonBeneficiary(NaturalBeneficiary naturalPersonBeneficiary) {
        this.naturalPersonBeneficiary = naturalPersonBeneficiary;
    }

    public LegalBeneficiary getLegalPersonBeneficiary() {
        return legalPersonBeneficiary;
    }

    public void setLegalPersonBeneficiary(LegalBeneficiary legalPersonBeneficiary) {
        this.legalPersonBeneficiary = legalPersonBeneficiary;
    }
}
