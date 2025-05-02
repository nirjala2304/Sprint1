package com.demo.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity // JPA entity
@Table(name = "gas") // Maps to 'gas' table
public class Gas {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
    @Column(name = "gas_id") // 'gas_id' column
    private int gasId;

    @Column(name = "gas_name") // 'gas_name' column
    private String gasName;

    @Column(name = "gas_price") // 'gas_price' column
    private BigDecimal gasPrice;

    @Column(name = "is_available") // 'is_available' column
    private boolean isAvailable;

    public Gas() {
    }

    public Gas(String gasName, BigDecimal gasPrice) {
        this.gasName = gasName;
        this.gasPrice = gasPrice;
        this.isAvailable = true;
    }

    public int getGasId() {
        return gasId;
    }

    public void setGasId(int gasId) {
        this.gasId = gasId;
    }

    public String getGasName() {
        return gasName;
    }

    public void setGasName(String gasName) {
        this.gasName = gasName;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Gas{" +
                "gasId=" + gasId +
                ", gasName='" + gasName + '\'' +
                ", gasPrice=" + gasPrice +
                ", isAvailable=" + isAvailable +
                '}';
    }
}