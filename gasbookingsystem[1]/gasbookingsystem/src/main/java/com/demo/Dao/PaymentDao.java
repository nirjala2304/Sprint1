package com.demo.Dao;

import com.demo.entity.Payment;
import java.util.List;

public interface PaymentDao {
    Payment getPaymentById(int payId); // Get payment by ID
    List<Payment> getAllPayments(); // Get all payments
    void createPayment(Payment payment); // Create a new payment
    void updatePayment(Payment payment); // Update existing payment
    void deletePayment(Payment payment); // Delete a payment
    Payment getPaymentByBookingId(int bookingId); // Get payment by booking ID
}