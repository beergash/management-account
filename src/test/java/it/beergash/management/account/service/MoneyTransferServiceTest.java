package it.beergash.management.account.service;

import it.beergash.management.account.exception.MoneyTransferException;
import it.beergash.management.account.model.MoneyTransfer;
import it.beergash.management.account.model.request.MoneyTransferRequest;
import it.beergash.management.account.model.response.GetAccountResponse;
import it.beergash.management.account.model.response.MoneyTransferResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Test class of service {@link MoneyTransferService}
 *
 * @author A.Aresta
 */
@RunWith(MockitoJUnitRunner.class)
public class MoneyTransferServiceTest {

    @InjectMocks
    private MoneyTransferService service;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void init() {
        ReflectionTestUtils.setField(service, "transferOperationUrl", "url_test");
        ReflectionTestUtils.setField(service, "authSchema", "authSchema_test");
        ReflectionTestUtils.setField(service, "apiKey", "apiKey_test");
    }

    @Test
    public void testExecuteMoneyTransfer() {
        MoneyTransferRequest req = new MoneyTransferRequest();
        MoneyTransferResponse resp = new MoneyTransferResponse();
        final String accIdTest = "accountid_test";
        final String monTransfId = "12345";
        final String status = "EXECUTED";
        MoneyTransfer mr = new MoneyTransfer();
        mr.setMoneyTransferId(monTransfId);
        mr.setStatus(status);
        resp.setPayload(mr);
        ResponseEntity<MoneyTransferResponse> respEntity = ResponseEntity.status(HttpStatus.OK)
                                                .body(resp);
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(HttpEntity.class), Mockito.any(Class.class), Mockito.anyString()))
                .thenReturn(respEntity);
        MoneyTransfer result = service.executeMoneyTransfer(accIdTest, req);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getMoneyTransferId(), monTransfId);
        Assert.assertEquals(result.getStatus(), status);
    }

    @Test(expected = MoneyTransferException.class)
    public void testExecuteMoneyTransferError() {
        ResponseEntity<GetAccountResponse> respEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GetAccountResponse());
        Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(HttpEntity.class), Mockito.any(Class.class), Mockito.anyString()))
                .thenReturn(respEntity);
        service.executeMoneyTransfer("", new MoneyTransferRequest());
    }


}
