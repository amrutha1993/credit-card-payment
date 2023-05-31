package com.project.creditcardpayment.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillPaymentResponse {

    private String paymentRefId;
    private String message;

}



