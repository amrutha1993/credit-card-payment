package com.project.creditcardpayment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "card_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Card {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "valid_till")
    private String validTill;

    @Column(name = "card_type")
    private String cardType;


}
