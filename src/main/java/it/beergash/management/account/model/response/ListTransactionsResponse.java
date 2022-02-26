package it.beergash.management.account.model.response;

import it.beergash.management.account.model.transaction.Transaction;

import java.util.List;

public class ListTransactionsResponse extends AbstractFabrickResponse {

    private TransactionsResponsePayload payload;

    public TransactionsResponsePayload getPayload() {
        return payload;
    }

    public void setPayload(TransactionsResponsePayload payload) {
        this.payload = payload;
    }

    public class TransactionsResponsePayload {
        private List<Transaction> list;

        public List<Transaction> getList() {
            return list;
        }

        public void setList(List<Transaction> list) {
            this.list = list;
        }
    }
}
