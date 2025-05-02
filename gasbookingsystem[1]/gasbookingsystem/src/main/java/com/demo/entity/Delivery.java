package com.demo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity // JPA entity
@Table(name = "delivery") // Maps to 'delivery' table
public class Delivery {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    @Column(name = "delivery_id") // 'delivery_id' column
    private int deliveryId;

    @Column(name = "booking_id") // 'booking_id' column
    private int bookingId; // Foreign key for booking

   // @Column(name = "delivery_address", length = 100) // 'delivery_address' column (max 100 chars)
    private String deliveryAddress;

    @Column(name = "delivery_date") // 'delivery_date' column
    private Date deliveryDate;

    public Delivery() {
    }

    public Delivery(int bookingId, String deliveryAddress, Date deliveryDate) {
        this.bookingId = bookingId;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
    }

    public Delivery(int deliveryId, int bookingId, String deliveryAddress, Date deliveryDate) {
        this.deliveryId = deliveryId;
        this.bookingId = bookingId;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", bookingId=" + bookingId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}