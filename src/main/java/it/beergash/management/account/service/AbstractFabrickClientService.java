package it.beergash.management.account.service;

import it.beergash.management.account.ApplicationConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

/**
 * Abstact super class for service client to fabrick server
 * conatins common utilities od fabrick servers api such as auth headers settings
 * @author A.Aresta
 */
public abstract class AbstractFabrickClientService {

    @Value("${fabrick.server.authSchema}")
    private String authSchema;

    @Value("${fabrick.server.apiKey}")
    private String apiKey;

    /**
     * set common auth headers such as: api-key and Auth-Schema
     * @return
     */
    protected HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(ApplicationConstants.API_KEY_HEADER_NAME, apiKey);
        headers.set(ApplicationConstants.AUTH_SCHEMA_HEADER_NAME, authSchema);
        return headers;
    }
}
