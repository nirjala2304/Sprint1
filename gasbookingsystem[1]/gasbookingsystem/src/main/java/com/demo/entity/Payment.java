package com.demo.entity;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private int payId;

    @Column(name = "booking_id")
    private int bookingId;

    @Column(name = "pay_date")
    private Date payDate;

    @Column(name = "pay_amount", precision = 10, scale = 2)
    private BigDecimal payAmount;

    @Column(name = "payment_method") // Add this field
    private String paymentMethod;

    @Column(name = "card_number") // Assuming you also want to store card number
    private String cardNumber;

    // Constructors (make sure you have one that includes paymentMethod)
    public Payment() {
    }

    public Payment(int bookingId, Date payDate, BigDecimal payAmount) {
        this.bookingId = bookingId;
        this.payDate = payDate;
        this.payAmount = payAmount;
    }

    public Payment(int bookingId, Date payDate, BigDecimal payAmount, String paymentMethod, String cardNumber) {
        this.bookingId = bookingId;
        this.payDate = payDate;
        this.payAmount = payAmount;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
    }

    // Getters and setters for all fields

    public int getPayId() {
        return payId;
    }

    public void setPayId(int payId) {
        this.payId = payId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPaymentMethod() { // Add this getter
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) { // Add this setter
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Payment{" +
               "payId=" + payId +
               ", bookingId=" + bookingId +
               ", payDate=" + payDate +
               ", payAmount=" + payAmount +
               ", paymentMethod='" + paymentMethod + '\'' +
               ", cardNumber='" + (cardNumber != null ? "****-****-****-" + cardNumber.substring(cardNumber.length() - 4) : null) + '\'' +
               '}';
    }
}