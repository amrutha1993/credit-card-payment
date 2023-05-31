package com.project.creditcardpayment.repository;

import com.project.creditcardpayment.entity.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentHistory, String> {

}
