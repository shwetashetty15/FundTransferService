package com.teckarch.fundtransfer.FundTransfer.Service;


import com.teckarch.fundtransfer.FundTransfer.Model.FundTransferModel;

import java.math.BigDecimal;
import java.util.List;

public interface FundTransferService {

    // To initiate a fund transfer
    FundTransferModel initiateTransfer(Long senderAccountId, Long receiverAccountId, BigDecimal amount);

    // To get all transactions for a given user
    List<FundTransferModel> getTransactionsByUserId(Long userId, String type, BigDecimal min, BigDecimal max);

    // To get transactions by account ID
    List<FundTransferModel> getTransactionsByAccountId(Long accountId);

    // To get the status/details of a given transaction
    FundTransferModel getTransactionDetails(Long transactionId);

    // To validate if a transaction is within the user/account limit
    boolean validateTransactionLimit(Long accountId, BigDecimal amount);

    // To get transaction limits for an account
    BigDecimal getTransactionLimits(Long accountId);

    // To create a scheduled transfer
    FundTransferModel createScheduledTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount, String frequency, String startDate, String endDate);

    // To update a scheduled transfer
    FundTransferModel updateScheduledTransfer(Long transferId, BigDecimal amount, String endDate);

    // To cancel a scheduled transfer
    void cancelScheduledTransfer(Long transferId);
}



