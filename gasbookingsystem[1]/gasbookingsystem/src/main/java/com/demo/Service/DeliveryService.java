package com.demo.Service;

import com.demo.entity.Delivery;
import java.util.List;

public interface DeliveryService {
    void createDelivery(Delivery delivery); // Create a new delivery
    Delivery getDeliveryById(int deliveryId); // Get delivery by ID
    List<Delivery> getDeliveriesByBookingId(int bookingId); // Get deliveries for a booking
    List<Delivery> getDeliveriesByCustomerId(int customerId); // Get deliveries for a customer
    void updateDelivery(Delivery delivery); // Update existing delivery
    void deleteDelivery(int deliveryId); // Delete delivery by ID
    Delivery getDeliveryByBookingId(int bookingId); // Get delivery by booking ID
    List<Delivery> getAllDeliveries(); // Get all deliveries
}