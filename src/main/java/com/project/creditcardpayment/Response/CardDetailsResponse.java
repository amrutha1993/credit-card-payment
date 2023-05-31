package com.project.creditcardpayment.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailsResponse {

    private String cardNumber;
    private String cardHolderName;
    private String emailId;
    private String validTill;
    private String cardType;

}
