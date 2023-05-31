package com.project.creditcardpayment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillPaymentRequest {
    @NotNull
    @NotEmpty(message = "Bill Number should not be empty!")
    private String billNumber;

    @NotNull
    @NotEmpty(message = "Card Number should not be empty!")
    private String cardNumber;

    @NotNull
    @NotEmpty(message = "Bill Amount should not be empty!")
    private BigDecimal billAmount;
}
