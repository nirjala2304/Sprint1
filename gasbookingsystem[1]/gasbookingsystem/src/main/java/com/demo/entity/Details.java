package com.demo.entity;

import javax.persistence.*;

@Entity // JPA entity
@Table(name = "details") // Maps to 'details' table
public class Details {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    @Column(name = "details_id") // 'details_id' column
    private int detailsId;

    @Column(name = "booking_id") // 'booking_id' column
    private int bookingId; // Foreign key for booking

    public Details() {
    }

    public Details(int bookingId) {
        this.bookingId = bookingId;
    }

    public Details(int detailsId, int bookingId) {
        this.detailsId = detailsId;
        this.bookingId = bookingId;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    @Override
    public String toString() {
        return "Details{" +
               "detailsId=" + detailsId +
               ", bookingId=" + bookingId +
               '}';
    }
}