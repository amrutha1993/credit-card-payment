package com.project.creditcardpayment.service.serviceImpl;

import com.project.creditcardpayment.Response.CardDetailsResponse;
import com.project.creditcardpayment.dto.CardDto;
import com.project.creditcardpayment.entity.Card;
import com.project.creditcardpayment.exception.CardDetailsAlreadyExistsException;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.repository.CreditCardRepository;
import com.project.creditcardpayment.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.project.creditcardpayment.constants.SetConstants.*;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    // Add Card details
    public void addCardDetails(CardDto cardDto) throws CardDetailsAlreadyExistsException {
        Optional<Card> optionalCard = Optional.ofNullable(creditCardRepository.findByCardNumber(cardDto.getCardNumber()));
        if (optionalCard.isPresent()) {
            throw new CardDetailsAlreadyExistsException(CARD_ALREADY_ADDED);
        } else {
            Card card = new Card();
            card.setCardNumber(cardDto.getCardNumber());
            saveCardDetails(card, cardDto);
        }
    }

    //Remove Card details
    public void removeCardDetails(String cardNumber) throws NotFoundException {
        Optional<Card> optionalCard = Optional.ofNullable(creditCardRepository.findByCardNumber(cardNumber));
        if (optionalCard.isPresent()) {
            creditCardRepository.delete(optionalCard.get());
        } else
            throw new NotFoundException(NOT_FOUND);
    }

    //Update Card Details
    public void updateCardDetails(CardDto cardDto) throws NotFoundException {
        Optional<Card> optionalCard = Optional.ofNullable(creditCardRepository.findByCardNumber(cardDto.getCardNumber()));
        if (optionalCard.isPresent()) {
            Card card = optionalCard.get();
            saveCardDetails(card, cardDto);
        } else {
            throw new NotFoundException(NOT_FOUND);
        }
    }

    //Show Card details
    public CardDetailsResponse showCardDetails(String cardNumber) throws NotFoundException {
        Optional<Card> optionalCard = Optional.ofNullable(creditCardRepository.findByCardNumber(cardNumber));
        if (!optionalCard.isPresent()) {
            throw new NotFoundException(NOT_FOUND);
        } else {
            Card card = optionalCard.get();
            return convertEntityToDto(card);
        }
    }

    public List<CardDetailsResponse> showAllCardDetails(String emailId) throws NotFoundException {
        List<Card> showAllCards = creditCardRepository.findAllByEmailId(emailId);
        if (showAllCards.isEmpty()) {
            throw new NotFoundException(CARD_NOT_FOUND);
        }
        return showAllCards.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    private Card saveCardDetails(Card card, CardDto cardDto) {
        card.setCardType(cardDto.getCardType());
        card.setCardHolderName(cardDto.getCardHolderName());
        card.setEmailId(cardDto.getEmailId());
        card.setValidTill(cardDto.getValidTill());
        creditCardRepository.save(card);
        return card;
    }

    private String maskCardNumber(String cardNumber, String mask) {
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == 'x') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }
        return maskedNumber.toString();
    }

    private CardDetailsResponse convertEntityToDto(Card card) {
        CardDetailsResponse cardDetailsResponse = new CardDetailsResponse();

        cardDetailsResponse.setCardNumber(maskCardNumber(card.getCardNumber(), CREDIT_CARD_NUMBER_MASK));
        cardDetailsResponse.setCardHolderName(card.getCardHolderName());
        cardDetailsResponse.setEmailId(card.getEmailId());
        cardDetailsResponse.setValidTill(card.getValidTill());
        cardDetailsResponse.setCardType(card.getCardType());
        return cardDetailsResponse;
    }

}


