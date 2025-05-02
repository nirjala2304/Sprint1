package com.demo.entity;

import javax.persistence.*;

@Entity // JPA entity
@Table(name = "customer") // Maps to 'customer' table
public class Customer {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    @Column(name = "cust_id") // 'cust_id' column
    private Integer custId;

    @Column(name = "cust_name", length = 100) // 'cust_name' column (max 100 chars)
    private String custName;

    @Column(name = "cust_mobile", length = 10) // 'cust_mobile' column (max 10 chars)
    private String custMobile;

    @Column(name = "cust_email", length = 100) // 'cust_email' column (max 100 chars)
    private String custEmail;

    @Column(name = "cust_address", columnDefinition = "TEXT") // 'cust_address' column (allows long text)
    private String custAddress;

    // Validation method for mobile number
    public boolean isValidMobileNumber() {
        if (this.custMobile == null || this.custMobile.length() != 10) {
            return false;
        }
        for (char c : this.custMobile.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public Customer() {
    }

    public Customer(String custName, String custMobile, String custEmail, String custAddress) {
        this.custName = custName;
        this.custMobile = custMobile;
        this.custEmail = custEmail;
        this.custAddress = custAddress;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
               "custId=" + custId +
               ", custName='" + custName + '\'' +
               ", custMobile='" + custMobile + '\'' +
               ", custEmail='" + custEmail + '\'' +
               ", custAddress='" + custAddress + '\'' +
               '}';
    }
}