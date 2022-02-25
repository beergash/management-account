package it.beergash.management.account.service;

import it.beergash.management.account.ApplicationConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

public abstract class AbstractAccountsService {

    @Value("${fabrick.server.authSchema}")
    private String authSchema;

    @Value("${fabrick.server.apiKey}")
    private String apiKey;

    public HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ApplicationConstants.API_KEY_HEADER_NAME, apiKey);
        headers.set(ApplicationConstants.AUTH_SCHEMA_HEADER_NAME, authSchema);
        return headers;
    }
}
