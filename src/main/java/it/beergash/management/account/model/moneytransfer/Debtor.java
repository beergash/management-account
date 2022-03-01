package it.beergash.management.account.model.moneytransfer;

public class Debtor {

    private String name;
    private DebtorAccount account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DebtorAccount getAccount() {
        return account;
    }

    public void setAccount(DebtorAccount account) {
        this.account = account;
    }
}
