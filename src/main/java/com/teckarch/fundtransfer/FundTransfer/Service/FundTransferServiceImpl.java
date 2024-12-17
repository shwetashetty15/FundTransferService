package com.teckarch.fundtransfer.FundTransfer.Service;

import com.teckarch.fundtransfer.FundTransfer.Model.FundTransferModel;
import com.teckarch.fundtransfer.FundTransfer.Repository.FundTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FundTransferServiceImpl implements FundTransferService {

    private final FundTransferRepository fundTransferRepository;

    @Autowired
    public FundTransferServiceImpl(FundTransferRepository fundTransferRepository) {
        this.fundTransferRepository = fundTransferRepository;
    }

    @Override
    public FundTransferModel initiateTransfer(Long senderAccountId, Long receiverAccountId, BigDecimal amount) {
        FundTransferModel transfer = new FundTransferModel();
        transfer.setSenderAccountId(senderAccountId);
        transfer.setReceiverAccountId(receiverAccountId);
        transfer.setAmount(amount);
        transfer.setStatus("pending");
        return fundTransferRepository.save(transfer);
    }

    @Override
    public List<FundTransferModel> getTransactionsByUserId(Long userId, String type, BigDecimal min, BigDecimal max) {
        // Query logic for user transactions, with filtering on type and amount range
        return fundTransferRepository.findBySenderAccountIdOrReceiverAccountId(userId, userId);  // Simplified logic, can be extended
    }

    @Override
    public List<FundTransferModel> getTransactionsByAccountId(Long accountId) {
        return fundTransferRepository.findBySenderAccountIdOrReceiverAccountId(accountId, accountId);
    }

    @Override
    public FundTransferModel getTransactionDetails(Long transactionId) {
        Optional<FundTransferModel> transaction = fundTransferRepository.findById(transactionId);
        return transaction.orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public boolean validateTransactionLimit(Long accountId, BigDecimal amount) {
        // Add logic to check if the transaction amount is within the account's or user's limit
        return true;  // Simplified for demonstration
    }

    @Override
    public BigDecimal getTransactionLimits(Long accountId) {
        // Logic to retrieve account's transaction limits (daily/weekly/monthly)
        return BigDecimal.valueOf(10000);  // Example limit, can be extended
    }

    @Override
    public FundTransferModel createScheduledTransfer(Long fromAccountId, Long toAccountId, BigDecimal amount, String frequency, String startDate, String endDate) {
        // Implement scheduled transfer creation logic here
        FundTransferModel scheduledTransfer = new FundTransferModel();
        scheduledTransfer.setSenderAccountId(fromAccountId);
        scheduledTransfer.setReceiverAccountId(toAccountId);
        scheduledTransfer.setAmount(amount);
        scheduledTransfer.setStatus("scheduled");
        return fundTransferRepository.save(scheduledTransfer);
    }

    @Override
    public FundTransferModel updateScheduledTransfer(Long transferId, BigDecimal amount, String endDate) {
        FundTransferModel transfer = fundTransferRepository.findById(transferId)
                .orElseThrow(() -> new RuntimeException("Scheduled transfer not found"));
        transfer.setAmount(amount);
        transfer.setStatus("updated");
        return fundTransferRepository.save(transfer);
    }

    @Override
    public void cancelScheduledTransfer(Long transferId) {
        FundTransferModel transfer = fundTransferRepository.findById(transferId)
                .orElseThrow(() -> new RuntimeException("Scheduled transfer not found"));
        transfer.setStatus("cancelled");
        fundTransferRepository.save(transfer);
    }
}
