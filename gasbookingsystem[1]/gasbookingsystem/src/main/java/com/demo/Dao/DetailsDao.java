package com.demo.Dao;

import com.demo.entity.Details;
import java.util.List;

public interface DetailsDao {
    Details getDetailsById(int detailsId); // Get details by ID
    List<Details> getAllDetails(); // Get all details
    List<Details> getDetailsByBookingId(int bookingId); // Get details by booking ID
}