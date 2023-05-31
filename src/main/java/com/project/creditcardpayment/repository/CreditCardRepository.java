package com.project.creditcardpayment.repository;

import com.project.creditcardpayment.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditCardRepository extends JpaRepository<Card, Long> {

    Card findByCardNumber(String cardNumber);

    @Query(value = "SELECT * FROM card_details c WHERE c.email_id = ?1", nativeQuery = true)
    List<Card> findAllByEmailId(String emailId);
}
