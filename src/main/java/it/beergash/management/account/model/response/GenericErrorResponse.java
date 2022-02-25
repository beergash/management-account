package it.beergash.management.account.model.response;

import it.beergash.management.account.model.ErrorModel;

import java.util.List;

/**
 * Generic Error Response returned by Controller in case of error
 *
 * @author A.Aresta
 */
public class GenericErrorResponse {

    private List<ErrorModel> errors;

    public GenericErrorResponse(List<ErrorModel> errors) {
        this.errors = errors;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
