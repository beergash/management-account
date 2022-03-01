package it.beergash.management.account.model.response;

import it.beergash.management.account.model.moneytransfer.MoneyTransfer;

public class MoneyTransferResponse extends AbstractFabrickResponse {

    private MoneyTransfer payload;

    public MoneyTransfer getPayload() {
        return payload;
    }

    public void setPayload(MoneyTransfer payload) {
        this.payload = payload;
    }
}
