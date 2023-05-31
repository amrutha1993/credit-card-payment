package com.project.creditcardpayment.repository;

import com.project.creditcardpayment.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query(value = "SELECT * FROM bill_details b WHERE b.card_number = ?1", nativeQuery = true)
    List<Bill> findAllByCardNumber(String cardNumber);

    Bill findByBillNumber(String billNumber);
}