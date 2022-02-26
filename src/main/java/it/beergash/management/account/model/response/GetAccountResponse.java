package it.beergash.management.account.model.response;

import it.beergash.management.account.model.Account;

public class GetAccountResponse extends AbstractFabrickResponse {

    private Account payload;

    public Account getPayload() {
        return payload;
    }

    public void setPayload(Account payload) {
        this.payload = payload;
    }
}
