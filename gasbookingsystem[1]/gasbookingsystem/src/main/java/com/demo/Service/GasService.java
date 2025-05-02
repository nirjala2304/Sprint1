package com.demo.Service;

import com.demo.entity.Gas;
import java.util.List;

public interface GasService {
    void createGas(Gas gas); // Create new gas
    Gas getGasById(int gasId); // Get gas by ID
    void updateGas(Gas gas); // Update existing gas
    void deleteGas(int gasId); // Delete gas by ID
    List<Gas> getAvailableGases(); // Get all available gases
    void updateGasAvailability(int gasId, boolean isAvailable); // Update gas availability status
}