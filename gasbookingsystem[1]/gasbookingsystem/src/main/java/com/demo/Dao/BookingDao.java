package com.demo.Dao;

import com.demo.entity.Booking;
import java.util.List;

public interface BookingDao {
    void createBooking(Booking booking); // Create a new booking
    Booking getBookingById(int bookingId); // Get booking by ID
    void deleteBooking(int bookingId); // Delete booking by ID
    void updateBooking(Booking booking); // Update existing booking
    List<Booking> getAllBookings(); // Get all bookings
    List<Booking> getBookingsByCustomerId(int customerId); // Get bookings by customer ID
    List<Booking> getBookingsByGasId(int gasId); // Get bookings by gas ID
}