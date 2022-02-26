package it.beergash.management.account.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import it.beergash.management.account.model.Account;
import it.beergash.management.account.model.request.ListTransactionsRequest;
import it.beergash.management.account.model.request.MoneyTransferRequest;
import it.beergash.management.account.model.response.MoneyTransferResponse;
import it.beergash.management.account.model.transaction.Transaction;
import it.beergash.management.account.service.GetAccountService;
import it.beergash.management.account.service.ListTransactionsService;
import it.beergash.management.account.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "ManagementAccountController")
public class ManagementAccountController {

    @Autowired
    private GetAccountService getAccountService;

    @Autowired
    private MoneyTransferService moneyTransferService;

    @Autowired
    private ListTransactionsService listTransactionsService;

    @GetMapping(value = "/get-account/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fornisce il saldo del conto", response = Account.class)
    public ResponseEntity<Account> getAccount(@PathVariable String accountId) {
        Account account = getAccountService.getAccount(accountId);
        return ResponseEntity.ok(account);
    }

    @PostMapping(value = "/money-transfer/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Effettua bonifico", response = Account.class)
    public ResponseEntity<MoneyTransferResponse> moneyTransfer(@PathVariable String accountId,
                                                               @Valid @RequestBody MoneyTransferRequest request) {
        MoneyTransferResponse result = moneyTransferService.executeMoneyTransfer(accountId, request);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/list-transactions/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fornisce la lista delle transazioni", response = List.class)
    public ResponseEntity<List<Transaction>> listTransactions(@Valid @RequestBody ListTransactionsRequest request) {
        List<Transaction> transactions = listTransactionsService.listTransactions(request);
        return ResponseEntity.ok(transactions);
    }
}
