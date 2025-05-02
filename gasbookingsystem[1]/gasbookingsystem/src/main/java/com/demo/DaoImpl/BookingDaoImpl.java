package com.demo.DaoImpl;

import com.demo.Dao.BookingDao;
import com.demo.entity.Booking;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;
import org.hibernate.HibernateException;
import java.util.List;

public class BookingDaoImpl implements BookingDao {

    @Override
    public void createBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            session.persist(booking); // Save the booking
            transaction.commit(); // Commit transaction
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("Hibernate error creating booking: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("General error creating booking: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Booking getBookingById(int bookingId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Booking.class, bookingId); // Get booking by ID
        } catch (HibernateException e) {
            System.err.println("Hibernate error getting booking by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.err.println("General error getting booking by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteBooking(int bookingId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            Booking booking = session.get(Booking.class, bookingId); // Get booking to delete
            if (booking != null) {
                session.remove(booking); // Delete the booking
            }
            transaction.commit(); // Commit transaction
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("Hibernate error deleting booking: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("General error deleting booking: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            session.merge(booking); // Update the booking
            transaction.commit(); // Commit transaction
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("Hibernate error updating booking: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("General error updating booking: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Booking", Booking.class).list(); // Get all bookings
        } catch (HibernateException e) {
            System.err.println("Hibernate error getting all bookings: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        } catch (Exception e) {
            System.err.println("General error getting all bookings: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @Override
    public List<Booking> getBookingsByCustomerId(int customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Booking WHERE cust_id = :customerId", Booking.class) // Query by customer ID
                           .setParameter("customerId", customerId)
                           .list();
        } catch (HibernateException e) {
            System.err.println("Hibernate error getting bookings by customer ID: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        } catch (Exception e) {
            System.err.println("General error getting bookings by customer ID: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }

    @Override
    public List<Booking> getBookingsByGasId(int gasId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Booking WHERE gas_id = :gasId", Booking.class) // Query by gas ID
                           .setParameter("gasId", gasId)
                           .list();
        } catch (HibernateException e) {
            System.err.println("Hibernate error getting bookings by gas ID: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        } catch (Exception e) {
            System.err.println("General error getting bookings by gas ID: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }
}