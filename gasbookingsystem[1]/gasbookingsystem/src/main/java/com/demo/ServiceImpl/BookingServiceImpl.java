// BookingServiceImpl.java (Implementation)
package com.demo.ServiceImpl;

import com.demo.Dao.BookingDao;
import com.demo.DaoImpl.BookingDaoImpl;
import com.demo.Service.BookingService;
import com.demo.entity.Booking;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private BookingDao bookingDao = new BookingDaoImpl();

    @Override
    public void createBooking(Booking booking) {
        bookingDao.createBooking(booking);
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingDao.getBookingById(bookingId);
    }

    @Override
    public void deleteBooking(int bookingId) {
        bookingDao.deleteBooking(bookingId);
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingDao.updateBooking(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    @Override
    public List<Booking> getBookingsByCustomerId(int customerId) {
        return bookingDao.getBookingsByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsByGasId(int gasId) {
        return bookingDao.getBookingsByGasId(gasId);
    }
}