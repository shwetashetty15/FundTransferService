package com.teckarch.fundtransfer.FundTransfer.Controller;

import com.teckarch.fundtransfer.FundTransfer.Model.FundTransferModel;
import com.teckarch.fundtransfer.FundTransfer.Service.FundTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/fund-transfer")
public class FundTransferController {

    @Autowired
    private FundTransferService fundTransferService;

    // Initiate Transfer
    @PostMapping
    public ResponseEntity<FundTransferModel> initiateTransfer(@RequestBody FundTransferModel transfer) {
        FundTransferModel initiatedTransfer = fundTransferService.initiateTransfer(transfer);
        return ResponseEntity.ok(initiatedTransfer);
    }

    // Get All Transactions of a Given User
    @GetMapping("/transactions")
    public ResponseEntity<List<FundTransferModel>> getAllTransactionsForUser(
            @RequestParam Long userId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max) {
        List<FundTransferModel> transactions = fundTransferService.getAllTransactionsForUser(userId);
        return ResponseEntity.ok(transactions);
    }

    // Get Transactions by Account and Amount Range
    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<List<FundTransferModel>> getTransactionsByAccountAndAmountRange(
            @PathVariable Long accountId,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max) {
        List<FundTransferModel> transactions = fundTransferService.getTransactionsByAccountAndAmountRange(accountId, min, max);
        return ResponseEntity.ok(transactions);
    }

    // Get Transaction by ID
    @GetMapping("/transactions/{transactionId}")
    public ResponseEntity<FundTransferModel> getTransactionById(@PathVariable Long transactionId) {
        return fundTransferService.getTransactionById(transactionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get Transactions by Status
    @GetMapping("/transactions/status/{status}")
    public ResponseEntity<List<FundTransferModel>> getTransactionsByStatus(@PathVariable String status) {
        List<FundTransferModel> transactions = fundTransferService.getTransactionsByStatus(status);
        return ResponseEntity.ok(transactions);
    }
}
