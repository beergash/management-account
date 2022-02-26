package it.beergash.management.account.exception;

/**
 * Specific exception for money transfer service
 * @author A.Aresta
 */
public class MoneyTransferException extends RuntimeException {

    public MoneyTransferException() {
    }

    public MoneyTransferException(String s) {
        super(s);
    }

    public MoneyTransferException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MoneyTransferException(Throwable throwable) {
        super(throwable);
    }
}
