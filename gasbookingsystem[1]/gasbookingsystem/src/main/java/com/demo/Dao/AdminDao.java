package com.demo.Dao;

import com.demo.entity.Admin;

public interface AdminDao {
    Admin getAdminById(int adminId); // Get admin by ID
    Admin getAdminByName(String adminName); // Get admin by name
    Admin getAdminByNameAndPassword(String adminName, String adminPassword); // Add this method declaration
}