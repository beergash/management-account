package it.beergash.management.account.service;

import it.beergash.management.account.exception.MoneyTransferException;
import it.beergash.management.account.model.moneytransfer.MoneyTransfer;
import it.beergash.management.account.model.request.MoneyTransferRequest;
import it.beergash.management.account.model.response.MoneyTransferResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service to execute money transfers
 * @author A.Aresta
 */
@Service
public class MoneyTransferService extends AbstractFabrickClientService {

    private static Logger LOGGER = LoggerFactory.getLogger(MoneyTransferService.class.getName());
    private static final String DEFAULT_ERROR_MESSAGE = "Errore tecnico  La condizione BP049 non e' prevista per il conto id %s";

    @Value("${fabrick.server.transferOperationUrl}")
    private String transferOperationUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * execute money transfer
     * @param accountId
     * @param mtr
     * @return
     */
    public MoneyTransfer executeMoneyTransfer(String accountId, MoneyTransferRequest mtr) {
        HttpHeaders headers = createAuthHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Time-Zone", "Europe/Rome");
        final HttpEntity<MoneyTransferRequest> entity = new HttpEntity<>(mtr, headers);
        ResponseEntity<MoneyTransferResponse> result = restTemplate.postForEntity(transferOperationUrl, entity, MoneyTransferResponse.class, accountId);
        if (result.getStatusCode() != HttpStatus.OK) {
            throw new MoneyTransferException(String.format(DEFAULT_ERROR_MESSAGE, accountId));
        }
        return result.getBody().getPayload();
    }
}
