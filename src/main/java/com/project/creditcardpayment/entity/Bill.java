package com.project.creditcardpayment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill_details")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bill_number", unique = true)
    private String billNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "bill_date")
    private LocalDateTime billDate;

    @Column(name = "bill_description")
    private String description;

    @Column(name = "bill_amount")
    private BigDecimal billAmount;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "payment_ref_id")
    private String paymentRefId;

}