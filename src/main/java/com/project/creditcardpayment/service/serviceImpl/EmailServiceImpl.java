package com.project.creditcardpayment.service.serviceImpl;

import com.project.creditcardpayment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    public void sendEmail(String recipient, String message, String subject, String paymentRefId) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(sender);
        mailMessage.setTo(recipient);
        if (null != paymentRefId) {
            mailMessage.setText(message + ". Your payment reference ID is " + paymentRefId);
        } else {
            mailMessage.setText(message);
        }
        mailMessage.setSubject(subject);
        javaMailSender.send(mailMessage);
    }

}