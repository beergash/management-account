package it.beergash.management.account.exception;

public class ManagementAccountException extends RuntimeException {

    public ManagementAccountException() {
    }

    public ManagementAccountException(String s) {
        super(s);
    }

    public ManagementAccountException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ManagementAccountException(Throwable throwable) {
        super(throwable);
    }
}
