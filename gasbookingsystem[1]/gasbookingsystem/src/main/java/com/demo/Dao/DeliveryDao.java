package com.demo.Dao;

import com.demo.entity.Delivery;
import java.util.List;

public interface DeliveryDao {
    void createDelivery(Delivery delivery); // Create a new delivery
    Delivery getDeliveryById(int deliveryId); // Get delivery by ID
    List<Delivery> getDeliveriesByBookingId(int bookingId); // Get deliveries by booking ID
    List<Delivery> getDeliveriesByCustomerId(int customerId); // Get deliveries by customer ID
    void updateDelivery(Delivery delivery); // Update existing delivery
    void deleteDelivery(int deliveryId); // Delete delivery by ID
    Delivery getDeliveryByBookingId(int bookingId); // Get delivery by booking ID
}