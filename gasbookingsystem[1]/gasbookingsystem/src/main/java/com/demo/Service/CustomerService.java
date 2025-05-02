package com.demo.Service;

import com.demo.entity.Customer;
import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer); // Create a new customer
    Customer getCustomerById(int customerId); // Get customer by ID

    void deleteCustomer(int customerId); // Delete customer by ID
    void updateCustomer(Customer customer); // Update existing customer
    List<Customer> getAllCustomers(); // Get all customers
}