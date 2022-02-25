package it.beergash.management.account.handler;

import it.beergash.management.account.ApplicationConstants;
import it.beergash.management.account.exception.ManagementAccountException;
import it.beergash.management.account.exception.MoneyTransferException;
import it.beergash.management.account.model.ErrorModel;
import it.beergash.management.account.model.response.GenericErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Application RestController Advices to catch service exception in controller execution
 * and set appropriate http status errors
 *
 * @author A.Aresta
 */
@RestControllerAdvice
public class ManagementAccountExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(ManagementAccountExceptionHandler.class.getName());

    /**
     * Handler of exception @{@link ValidationException}
     * @param
     * @return error message
     */
    @ExceptionHandler({ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorResponse handleValidationException(ValidationException e) {
        LOGGER.error("Validation Exception: " + e.getMessage());
        ErrorModel em = new ErrorModel(ApplicationConstants.VALIDATION_ERROR_CODE, e.getMessage());
        GenericErrorResponse error = new GenericErrorResponse(Arrays.asList(em));
        return error;
    }

     /**
     * Handler of exception @{@link MethodArgumentNotValidException}
     * @param
     * @return error message
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        LOGGER.error("MethodArgumentNotValidException Exception: " + e.getMessage());
        List<ErrorModel> errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(f -> {
                    String fieldName = ((FieldError) f).getField();
                    String message = f.getDefaultMessage();
                    return new ErrorModel(ApplicationConstants.METHOD_ARG_NOT_VALID_ERROR_CODE, String.format("Property %s is not valid because %s", fieldName, message));
                }).collect(Collectors.toList());
        return new GenericErrorResponse(errorMessage);
    }

    /**
     * Handler of generic exception @{@link Exception}
     * @param
     * @return error message
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericErrorResponse handleGenericException(Exception e) {
        LOGGER.error("MethodArgumentNotValidException Exception: " + e.getMessage());
        ErrorModel em = new ErrorModel(ApplicationConstants.GENERIC_ERROR_CODE, e.getMessage());
        GenericErrorResponse error = new GenericErrorResponse(Arrays.asList(em));
        return error;
    }

    /**
     * Handler of custom ManagementAccount exception @{@link Exception}
     */
    @ExceptionHandler({ManagementAccountException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericErrorResponse handleManagementAccountException(ManagementAccountException e) {
        LOGGER.error("MethodArgumentNotValidException Exception: " + e.getMessage());
        ErrorModel em = new ErrorModel(ApplicationConstants.MGMT_ACCOUNTS_APPLICATION_ERROR_CODE, e.getMessage());
        GenericErrorResponse error = new GenericErrorResponse(Arrays.asList(em));
        return error;
    }

    /**
     * Handler of custom MoneyTransferException @{@link Exception}
     */
    @ExceptionHandler({MoneyTransferException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GenericErrorResponse handleMoneyTransferException(MoneyTransferException e) {
        LOGGER.error("MoneyTransferException: " + e.getMessage());
        ErrorModel em = new ErrorModel(ApplicationConstants.MONEY_TRANSFER_SERVICE_ERROR_CODE, e.getMessage());
        GenericErrorResponse error = new GenericErrorResponse(Arrays.asList(em));
        return error;
    }
}
