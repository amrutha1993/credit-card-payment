package com.project.creditcardpayment.service;

import com.project.creditcardpayment.Response.BillPaymentResponse;
import com.project.creditcardpayment.dto.BillDetailsDto;
import com.project.creditcardpayment.dto.BillPaymentRequest;
import com.project.creditcardpayment.exception.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillService {

    List<BillDetailsDto> viewBills(String cardNumber) throws NotFoundException;

    BillPaymentResponse billPayment(BillPaymentRequest billPaymentRequest) throws NotFoundException;

}
