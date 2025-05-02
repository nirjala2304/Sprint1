package com.demo.Service;

import com.demo.entity.Admin;
import java.util.List;

public interface AdminService {
    Admin getAdminById(int adminId); // Get admin by ID
    Admin getAdminByName(String adminName); // Get admin by name
    void createAdmin(Admin admin); // Create new admin
    void updateAdmin(Admin admin); // Update existing admin
    void deleteAdmin(int adminId); // Delete admin by ID
    Admin getAdminByNameAndPassword(String adminName, String adminPassword); // Get admin by name and password
    List<Admin> getAllAdmins(); // Get all admins
}