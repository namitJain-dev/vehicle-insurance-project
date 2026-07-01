package com.genc.payment.controller;

import com.genc.payment.model.Payment;
import com.genc.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //1 Safe GET Route (Always resolves to this URL)
    @GetMapping("/checkout")
    public String showPaymentPage(
            @RequestParam(defaultValue = "101") int incomingPolicyId,
            @RequestParam(defaultValue = "6000.00") double incomingAmount,
            Model model) {

        //if arriving fresh, create a new form.
        //if arriving from a redirect, Spring automatically passes the flashed data.
        if (!model.containsAttribute("paymentForm")) {
            Payment newPayment = new Payment();
            newPayment.setPolicyId(incomingPolicyId);
            newPayment.setAmount(incomingAmount);
            model.addAttribute("paymentForm", newPayment);
        }

        return "checkout";
    }

    //2 process Order and REDIRECT
    @PostMapping("/initiate")
    public String initiatePayment(@ModelAttribute("paymentForm") Payment payment, RedirectAttributes redirectAttributes) {
        try {
            String orderId = paymentService.createRazorpayOrder(payment.getAmount());

            // Flash Attributes survive exactly ONE redirect, then disappear safely
            redirectAttributes.addFlashAttribute("orderId", orderId);
            redirectAttributes.addFlashAttribute("paymentForm", payment);
            redirectAttributes.addFlashAttribute("triggerCheckout", true);
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Gateway Error: " + e.getMessage());
        }

        // REDIRECT back to the GET route instead of rendering the template directly
        return "redirect:/payment/checkout";
    }

    //3 process Success and REDIRECT
    @PostMapping("/success")
    public String handleSuccess(@ModelAttribute("paymentForm") Payment payment, RedirectAttributes redirectAttributes) {
        try {
            paymentService.processSuccessPayment(payment);

            redirectAttributes.addFlashAttribute("finalPaymentId", payment.getPaymentId());
            redirectAttributes.addFlashAttribute("finalStatus", payment.getPaymentStatus());
            redirectAttributes.addFlashAttribute("successMessage", "Payment Cleared! Receipt sent to " + payment.getUserEmail());
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Database Error: " + e.getMessage());
        }

        //REDIRECT back to the GET route
        return "redirect:/payment/checkout";
    }
}