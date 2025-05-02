package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA entity
@Table(name = "admin") // Maps to 'admin' table
public class Admin {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
	@Column(name = "admin_id") // 'admin_id' column
	private int adminId;

	@Column(name = "admin_name") // 'admin_name' column
	private String adminName;

	@Column(name = "admin_password") // 'admin_password' column
	private String adminPassword;

	// Constructors
	public Admin() {
	}

	public Admin(String adminName, String adminPassword) {
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	public Admin(int adminId, String adminName, String adminPassword) {
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	// Getters and Setters
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"adminId=" + adminId +
				", adminName='" + adminName + '\'' +
				", adminPassword='" + adminPassword + '\'' +
				'}';
	}
}