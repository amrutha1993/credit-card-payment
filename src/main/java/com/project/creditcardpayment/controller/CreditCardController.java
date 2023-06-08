package com.project.creditcardpayment.controller;

import com.project.creditcardpayment.dto.CardDto;
import com.project.creditcardpayment.exception.CardDetailsAlreadyExistsException;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.repository.CreditCardRepository;
import com.project.creditcardpayment.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Log4j2
@RequestMapping("/card")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/add")
    public ResponseEntity<?> addCard(@RequestBody @Valid CardDto cardDto) {
        try {
            creditCardService.addCardDetails(cardDto);
            return ResponseEntity.ok().body("Card details added successfully");
        } catch (CardDetailsAlreadyExistsException e) {
            log.error(e);
            return ResponseEntity.badRequest().body("Card details already exists!");
        }


    }

    @DeleteMapping("/remove/{cardNumber}")
    public ResponseEntity<?> removeCard(@PathVariable String cardNumber) {
        try {
            creditCardService.removeCardDetails(cardNumber);
            return ResponseEntity.ok().body("Card details removed successfully");
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest().body("Card details not found! Please recheck");
        }
    }

    @PutMapping("/update")
    private ResponseEntity<?> updateCard(@RequestBody @Valid CardDto cardDto) {
        try {
            creditCardService.updateCardDetails(cardDto);
            return ResponseEntity.ok().body("Card details updated successfully.");
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest().body("Card details not found!");
        }
    }

    @GetMapping("/show/details/{cardNumber}")
    private ResponseEntity<?> showCard(@PathVariable String cardNumber) {
        try {

            return ResponseEntity.ok().body(creditCardService.showCardDetails(cardNumber));
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest().body("Card details not found!");
        }
    }

    @GetMapping("/show/all/details/{emailId}")
    private ResponseEntity<?> showAllCard(@PathVariable String emailId) {
        try {
            return ResponseEntity.ok().body(creditCardService.showAllCardDetails(emailId));
        } catch (NotFoundException e) {
            log.error(e);
            return ResponseEntity.badRequest().body("Card details not found!");
        }
    }

}
