package com.teckarch.fundtransfer.FundTransfer.Repository;

import java.math.BigDecimal;
import com.teckarch.fundtransfer.FundTransfer.Model.FundTransferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundTransferRepository extends JpaRepository<FundTransferModel, Long> {
    List<FundTransferModel> findBySenderAccountIdOrReceiverAccountId(Long senderAccountId, Long receiverAccountId);
    List<FundTransferModel> findByStatus(String status);
    List<FundTransferModel> findBySenderAccountIdAndAmountBetween(Long accountId, BigDecimal min, BigDecimal max);
}

