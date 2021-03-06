package it.beergash.management.account.service;

import it.beergash.management.account.exception.ManagementAccountException;
import it.beergash.management.account.model.Account;
import it.beergash.management.account.model.response.GetAccountResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

/**
 * Service to retrieve and manage accounts
 * @author A.Aresta
 */
@Service
public class GetAccountService extends AbstractFabrickClientService {

    private static Logger LOGGER = LoggerFactory.getLogger(GetAccountService.class.getName());

    @Value("${fabrick.server.listAccountsUrl}")
    private String listAccountsUrl;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get account by account id
     * @param accountId
     * @return
     */
    public Account getAccount(String accountId) {
        String completeUrl = String.format("%s/%s", listAccountsUrl, accountId);
        HttpHeaders headers = createAuthHeaders();
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<GetAccountResponse> result = restTemplate.exchange(completeUrl, HttpMethod.GET, entity, GetAccountResponse.class);
        if (result.getStatusCode() != HttpStatus.OK) {
            String errorsMessage = CollectionUtils.isEmpty(result.getBody().getErrors()) ?
                    "" :
                    result.getBody().getErrors().stream()
                            .map(e -> e.getCode() + " - " + e.getDescription())
                            .collect(Collectors.joining(";"));
            throw new ManagementAccountException("Service Get Accounts getting errors: "+ errorsMessage);
        }
        return result.getBody().getPayload();
    }
}
