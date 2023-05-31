package com.project.creditcardpayment.controller;

import com.project.creditcardpayment.dto.BillPaymentRequest;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.repository.BillRepository;
import com.project.creditcardpayment.service.BillService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/user/bills")
public class BillPaymentController {

    @Autowired
    BillService billService;

    @Autowired
    BillRepository billRepository;


    @GetMapping("/view")
    public ResponseEntity<?> viewGeneratedBills(@RequestParam String cardNumber) {
        try {
            return ResponseEntity.ok().body(billService.viewBills(cardNumber));
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest().body("No bills found!");
        }

    }

    @PutMapping("/pay")
    public ResponseEntity<?> payBills(@RequestBody BillPaymentRequest billPaymentRequest) {
        try {
            return ResponseEntity.ok().body(billService.billPayment(billPaymentRequest));
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest().body("Bill Not Found");
        }
    }

}
