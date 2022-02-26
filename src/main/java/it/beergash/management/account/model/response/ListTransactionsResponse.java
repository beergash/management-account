package it.beergash.management.account.model.response;

public class ListTransactionsResponse extends AbstractFabrickResponse {

    private TransactionsResponsePayload payload;

    public TransactionsResponsePayload getPayload() {
        return payload;
    }

    public void setPayload(TransactionsResponsePayload payload) {
        this.payload = payload;
    }

}
