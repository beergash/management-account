package it.beergash.management.account.service;

import it.beergash.management.account.exception.ManagementAccountException;
import it.beergash.management.account.model.request.ListTransactionsRequest;
import it.beergash.management.account.model.response.ListTransactionsResponse;
import it.beergash.management.account.model.response.TransactionsResponsePayload;
import it.beergash.management.account.model.transaction.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Test class of service {@link ListTransactionsService}
 * @author A.Aresta
 */
@RunWith(MockitoJUnitRunner.class)
public class ListTransactionsServiceTest {

    public static final double DOUBLE_THRESHOLD = 0.001;
    @InjectMocks
    private ListTransactionsService service;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void init() {
        ReflectionTestUtils.setField(service, "listTransactionsUrl", "url_test");
        ReflectionTestUtils.setField(service, "authSchema", "authSchema_test");
        ReflectionTestUtils.setField(service, "apiKey", "apiKey_test");
    }

    @Test
    public void testListTransactions() {
        final String transactionIdTest = "12345";
        final double amountTest = 200D;
        ListTransactionsResponse resp = new ListTransactionsResponse();
        TransactionsResponsePayload payload = new TransactionsResponsePayload();
        Transaction transaction = new Transaction();
        transaction.setTransactionId(transactionIdTest);
        transaction.setAmount(amountTest);
        List<Transaction> transactions = Arrays.asList(transaction);
        payload.setList(transactions);
        resp.setPayload(payload);
        ResponseEntity<ListTransactionsResponse> respEntity = ResponseEntity.status(HttpStatus.OK)
                                                .body(resp);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class), ArgumentMatchers.<String>any()))
                .thenReturn(respEntity);
        List<Transaction> result = service.listTransactions(new ListTransactionsRequest());
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(result.get(0).getTransactionId(), transactionIdTest);
        Assert.assertEquals(result.get(0).getAmount(), amountTest, DOUBLE_THRESHOLD);
    }

    @Test(expected = ManagementAccountException.class)
    public void testListTransactionsWithError() {
        ResponseEntity<ListTransactionsResponse> respEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ListTransactionsResponse());
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class), ArgumentMatchers.<String>any()))
                .thenReturn(respEntity);
        service.listTransactions(new ListTransactionsRequest());
    }


}
