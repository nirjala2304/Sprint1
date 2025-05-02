package com.demo.Service;

import com.demo.entity.Booking;
import java.util.List;

public interface BookingService {
    void createBooking(Booking booking); // Create a new booking
    Booking getBookingById(int bookingId); // Get booking by ID
    void deleteBooking(int bookingId); // Delete booking by ID
    void updateBooking(Booking booking); // Update existing booking
    List<Booking> getAllBookings(); // Get all bookings
    List<Booking> getBookingsByCustomerId(int customerId); // Get bookings for a customer
    List<Booking> getBookingsByGasId(int gasId); // Get bookings for a gas type
}