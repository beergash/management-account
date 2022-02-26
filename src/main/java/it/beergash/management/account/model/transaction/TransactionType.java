package it.beergash.management.account.model.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionType {

    @JsonProperty("enumeration")
    private String type;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
