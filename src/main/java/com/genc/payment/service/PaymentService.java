package com.genc.payment.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import com.genc.payment.model.Payment;
import com.genc.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class PaymentService {

    @Autowired private PaymentRepository paymentRepository;
    @Autowired private EmailService emailService;

    @Value("${razorpay.key.id}") private String keyId;
    @Value("${razorpay.key.secret}") private String keySecret;

    public String createRazorpayOrder(double amount) throws Exception {
        RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", (int)(amount * 100));
        orderRequest.put("currency", "INR");
        return razorpay.orders.create(orderRequest).get("id");
    }

    public void processSuccessPayment(Payment payment) {
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentStatus("SUCCESS");

        paymentRepository.save(payment);

        String subject = "Payment SUCCESS: Policy ID " + payment.getPolicyId();
        String body = "Dear customer,\n\nYour premium payment of ₹" + payment.getAmount() +
                " was successful.\nDatabase Payment ID: " + payment.getPaymentId() +
                "\nBank Transaction ID: " + payment.getTransactionId();

        try {
            emailService.sendSimpleEmail(payment.getUserEmail(), subject, body);
        } catch (Exception e) {
            System.err.println("SMTP Email failed: " + e.getMessage());
        }
    }
}