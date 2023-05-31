package com.project.creditcardpayment.service.serviceImpl;

import com.project.creditcardpayment.Response.BillPaymentResponse;
import com.project.creditcardpayment.dto.BillDetailsDto;
import com.project.creditcardpayment.dto.BillPaymentRequest;
import com.project.creditcardpayment.entity.Bill;
import com.project.creditcardpayment.entity.PaymentHistory;
import com.project.creditcardpayment.exception.NotFoundException;
import com.project.creditcardpayment.repository.BillRepository;
import com.project.creditcardpayment.repository.PaymentRepository;
import com.project.creditcardpayment.service.BillService;
import com.project.creditcardpayment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.project.creditcardpayment.constants.SetConstants.BILL_NOT_FOUND;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    EmailService emailService;

    //View bill details

    @Override
    public List<BillDetailsDto> viewBills(String cardNumber) throws NotFoundException {
        List<Bill> showAllBills = billRepository.findAllByCardNumber(cardNumber);
        if (showAllBills.isEmpty()) {
            throw new NotFoundException(BILL_NOT_FOUND);
        }
        return showAllBills.stream()
                .map(this::convertEntityToDto)
                .toList();

    }

    // Bill Payment
    @Override
    public BillPaymentResponse billPayment(BillPaymentRequest billPaymentRequest) throws NotFoundException {
        Optional<Bill> payBill = Optional.ofNullable(billRepository.findByBillNumber(billPaymentRequest.getBillNumber()));
        BillPaymentResponse billPaymentResponse = new BillPaymentResponse();
        if (!payBill.isPresent()) {
            throw new NotFoundException(BILL_NOT_FOUND);
        }
        if (billPaymentRequest.getCardNumber().equals(payBill.get().getCardNumber())) {
            if (payBill.get().getIsPaid().equals(false)) {
                if (billPaymentRequest.getBillAmount().equals(payBill.get().getBillAmount())) {
                    String paymentRefId = UUID.randomUUID().toString();
                    PaymentHistory payment = new PaymentHistory();
                    payBill.get().setIsPaid(true);
                    payment.setPaymentRefId(paymentRefId);
                    payment.setBillNumber(payBill.get().getBillNumber());
                    payment.setAmount(payBill.get().getBillAmount());
                    payment.setPaymentDate(LocalDateTime.now());
                    payment.setPaymentStatus("SUCCESS");
                    billPaymentResponse.setMessage("Bill payment successful");
                    billPaymentResponse.setPaymentRefId(paymentRefId);
                    paymentRepository.save(payment);
                    billRepository.save(payBill.get());
                    emailService.sendEmail(payBill.get().getEmailId(), "Payment is successful for your bill",
                            "Payment Success for Bill No - " + payBill.get().getBillNumber(), paymentRefId);
                } else {
                    billPaymentResponse.setMessage("Bill amount does not match!");
                }
            } else {
                billPaymentResponse.setMessage("Bill already paid");
            }

        } else {
            billPaymentResponse.setMessage("Please check the card number!");
        }

        return billPaymentResponse;
    }


    //Convert Entity to Dto
    private BillDetailsDto convertEntityToDto(Bill bill) {
        BillDetailsDto billDto = new BillDetailsDto();

        billDto.setBillNumber(bill.getBillNumber());
        billDto.setBillAmount(bill.getBillAmount());
        billDto.setBillDate(bill.getBillDate().toLocalDate());
        billDto.setDescription(bill.getDescription());
        billDto.setCardNumber(bill.getCardNumber());
        billDto.setEmailId(bill.getEmailId());
        billDto.setIsPaid(bill.getIsPaid());
        return billDto;
    }

}
