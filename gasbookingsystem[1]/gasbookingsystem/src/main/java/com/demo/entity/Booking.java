package com.demo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity // JPA entity
@Table(name = "booking") // Maps to 'booking' table
public class Booking {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generated ID
	@Column(name = "booking_id") // 'booking_id' column
	private int bookingId;

	@Column(name = "cust_id") // 'cust_id' column
	private int custId;

	@Column(name = "gas_id") // 'gas_id' column
	private int gasId;

	@Column(name = "booking_date") // 'booking_date' column
	private Date bookingDate;

	public Booking() {}

	public Booking(int custId, int gasId, Date bookingDate) {
		this.custId = custId;
		this.gasId = gasId;
		this.bookingDate = bookingDate;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getGasId() {
		return gasId;
	}

	public void setGasId(int gasId) {
		this.gasId = gasId;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Booking{" +
				"bookingId=" + bookingId +
				", custId=" + custId +
				", gasId=" + gasId +
				", bookingDate=" + bookingDate +
				'}';
	}
}