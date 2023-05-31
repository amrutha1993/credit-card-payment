package com.project.creditcardpayment.service;


import com.project.creditcardpayment.Response.CardDetailsResponse;
import com.project.creditcardpayment.dto.CardDto;
import com.project.creditcardpayment.entity.Card;
import com.project.creditcardpayment.exception.CardDetailsAlreadyExistsException;
import com.project.creditcardpayment.exception.NotFoundException;

import java.util.List;

public interface CreditCardService {

    void addCardDetails(CardDto cardDto) throws CardDetailsAlreadyExistsException;

    void removeCardDetails(String cardNumber) throws NotFoundException;

    void updateCardDetails(CardDto cardDto) throws NotFoundException;

    CardDetailsResponse showCardDetails(String cardNumber) throws NotFoundException;

    List<CardDetailsResponse> showAllCardDetails(String emailId) throws  NotFoundException;
}
