package it.beergash.management.account.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.beergash.management.account.model.FabrickErrorModel;

import java.util.List;

public class AbstractFabrickResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FabrickErrorModel> errors;

    public List<FabrickErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<FabrickErrorModel> errors) {
        this.errors = errors;
    }
}
