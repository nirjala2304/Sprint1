package com.demo.Service;

import com.demo.entity.Payment;
import java.util.List;

public interface PaymentService {
    Payment getPaymentById(int payId); // Get payment by ID
    List<Payment> getAllPayments(); // Get all payments
    void createPayment(Payment payment); // Create new payment
    void updatePayment(Payment payment); // Update existing payment
    void deletePayment(Payment payment); // Delete payment
    Payment getPaymentByBookingId(int bookingId); // Get payment by booking ID
}