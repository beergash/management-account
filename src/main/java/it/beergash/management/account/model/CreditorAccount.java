package it.beergash.management.account.model;

import javax.validation.constraints.NotBlank;

public class CreditorAccount {

    @NotBlank(message = "account code must not be empty")
    private String accountCode;
    private String bicCode;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getBicCode() {
        return bicCode;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }
}
