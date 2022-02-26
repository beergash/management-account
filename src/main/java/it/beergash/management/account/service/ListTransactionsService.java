package it.beergash.management.account.service;

import it.beergash.management.account.exception.ManagementAccountException;
import it.beergash.management.account.model.request.ListTransactionsRequest;
import it.beergash.management.account.model.response.ListTransactionsResponse;
import it.beergash.management.account.model.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service to retrieve transactions
 */
@Service
public class ListTransactionsService extends AbstractFabrickClientService {

    private static Logger LOGGER = LoggerFactory.getLogger(ListTransactionsService.class.getName());

    @Value("${fabrick.server.listTransactionsUrl}")
    private String listTransactionsUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<Transaction> listTransactions(ListTransactionsRequest request) {
        Objects.requireNonNull(request, "ListTransactionsRequest must not be null");
        String completeUrl = String.format("%s?fromAccountingDate=%s&toAccountingDate=%s", listTransactionsUrl, request.getFromAccountingDate(), request.getToAccountingDate());
        HttpHeaders headers = createAuthHeaders();
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<ListTransactionsResponse> result = restTemplate.exchange(completeUrl, HttpMethod.GET, entity, ListTransactionsResponse.class, request.getAccountId());
        if (result.getStatusCode() != HttpStatus.OK) {
            String errorsMessage = CollectionUtils.isEmpty(result.getBody().getErrors()) ?
                    "" :
                    result.getBody().getErrors().stream()
                            .map(e -> e.getCode() + " - " + e.getDescription())
                            .collect(Collectors.joining(";"));
            throw new ManagementAccountException("Service Get list transactions getting errors: "+ errorsMessage);
        }
        return result.getBody().getPayload().getList();
    }
}
