package it.beergash.management.account.model.response;

import it.beergash.management.account.model.Account;

public class GetAccountResponse extends AbstractFabrickResponse {

    private String status;
    private Account payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getPayload() {
        return payload;
    }

    public void setPayload(Account payload) {
        this.payload = payload;
    }
}
