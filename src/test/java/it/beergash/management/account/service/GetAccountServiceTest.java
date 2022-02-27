package it.beergash.management.account.service;

import it.beergash.management.account.exception.ManagementAccountException;
import it.beergash.management.account.model.Account;
import it.beergash.management.account.model.response.GetAccountResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Test class of service {@link GetAccountService}
 *
 * @author A.Aresta
 */
@RunWith(MockitoJUnitRunner.class)
public class GetAccountServiceTest {

    @InjectMocks
    private GetAccountService service;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void init() {
        ReflectionTestUtils.setField(service, "listAccountsUrl", "url_test");
        ReflectionTestUtils.setField(service, "authSchema", "authSchema_test");
        ReflectionTestUtils.setField(service, "apiKey", "apiKey_test");
    }

    @Test
    public void testGetAccount() {
        GetAccountResponse resp = new GetAccountResponse();
        Account account = new Account();
        final String accIdTest = "accountid_test";
        final String currencyTest = "GBP";
        account.setAccountId(accIdTest);
        account.setCurrency(currencyTest);
        resp.setPayload(account);
        ResponseEntity<GetAccountResponse> respEntity = ResponseEntity.status(HttpStatus.OK)
                                                .body(resp);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class)))
                .thenReturn(respEntity);
        Account result = service.getAccount(accIdTest);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getAccountId(), accIdTest);
        Assert.assertEquals(result.getCurrency(), currencyTest);
    }

    @Test(expected = ManagementAccountException.class)
    public void testGetAccountWithError() {
        ResponseEntity<GetAccountResponse> respEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GetAccountResponse());
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class)))
                .thenReturn(respEntity);
        service.getAccount("account_id");
    }


}
