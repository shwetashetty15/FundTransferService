package com.teckarch.fundtransfer.FundTransfer.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "fund_transfers")
@Data  // Lombok will generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor  // Lombok will generate a no-argument constructor
@AllArgsConstructor
public class FundTransferModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transferId;

    @Column(nullable = false)
    private Long senderAccountId;

    @Column(nullable = false)
    private Long receiverAccountId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 20)
    private String status = "pending";

    @Column(nullable = false)
    private LocalDateTime initiatedAt = LocalDateTime.now();

    private LocalDateTime completedAt;


}

