package it.beergash.management.account.model;

import javax.validation.constraints.NotBlank;

public class LegalBeneficiary {

    @NotBlank
    private String fiscalCode;
    private String legalRepresentativeFiscalCode;

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getLegalRepresentativeFiscalCode() {
        return legalRepresentativeFiscalCode;
    }

    public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
        this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
    }
}
