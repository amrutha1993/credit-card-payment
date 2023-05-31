package com.project.creditcardpayment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailsDto {

    @NotNull
    @NotEmpty(message = "Bill Number should not be empty!")
    private String billNumber;

    @NotNull
    @NotEmpty(message = "Card Number should not be empty!")
    private String cardNumber;

    @NotNull
    @NotEmpty(message = "Email ID should not be empty!")
    private String emailId;

    @NotNull
    @NotEmpty(message = "Bill Date should not be empty!")
    private LocalDate billDate;

    @NotNull
    @NotEmpty(message = "Bill Description should not be empty!")
    private String description;

    @NotNull
    @NotEmpty(message = "Bill Amount should not be empty!")
    private BigDecimal billAmount;

    @NotNull
    @NotEmpty(message = "Flag should not be empty!")
    private Boolean isPaid = false;

}
