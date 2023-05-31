package com.project.creditcardpayment.service;


public interface EmailService {

    void sendEmail(String recipient, String message, String subject, String paymentRefId);



}

