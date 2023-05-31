package com.project.creditcardpayment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_history")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentHistory {


    @Id
    @Column(name = "payment_refId")
    private String paymentRefId;

    @Column(name = "bill_number")
    private String billNumber;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_status")
    private String paymentStatus;

}