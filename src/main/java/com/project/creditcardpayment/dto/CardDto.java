package com.project.creditcardpayment.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    @NotNull
    @NotEmpty(message = "Card Number should not be empty!")
    @Size(min = 16, max = 16)
    @Pattern(regexp = "^\\d{10}$", message = "16 digit card number format")
    private String cardNumber;

    @NotNull
    @NotEmpty(message = "Card Holder Name should not be empty!")
    private String cardHolderName;

    @NotNull
    @NotEmpty(message = "Email should not be empty!")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String emailId;

    @NotNull
    @NotEmpty(message = "Valid Till date should not be empty!")
    @Pattern(regexp = "(?:0?[1-9]|1[0-2])/[0-9]{2}", message = "Date format should be MM/YY")
    private String validTill;


    @NotNull
    @NotEmpty(message = "Card Type should not be empty!")
    private String cardType;

}
