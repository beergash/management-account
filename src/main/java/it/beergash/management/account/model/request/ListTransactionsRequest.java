package it.beergash.management.account.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;


public class ListTransactionsRequest {

    @NotNull(message = "Account id must not be null")
    private String accountId;
    @NotNull(message = "fromAccountingDate must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String fromAccountingDate;
    @NotNull(message = "toAccountingDate must not be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String toAccountingDate;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFromAccountingDate() {
        return fromAccountingDate;
    }

    public void setFromAccountingDate(String fromAccountingDate) {
        this.fromAccountingDate = fromAccountingDate;
    }

    public String getToAccountingDate() {
        return toAccountingDate;
    }

    public void setToAccountingDate(String toAccountingDate) {
        this.toAccountingDate = toAccountingDate;
    }
}
