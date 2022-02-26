package it.beergash.management.account.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Creditor {

    @NotBlank(message = "creditor name must not be null")
    private String name;

    @Valid
    @NotNull(message = "creditor account must not be null")
    private CreditorAccount account;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreditorAccount getAccount() {
        return account;
    }

    public void setAccount(CreditorAccount account) {
        this.account = account;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
