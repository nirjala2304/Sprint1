package com.demo.DaoImpl;

import com.demo.Dao.DeliveryDao;
import com.demo.entity.Delivery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import Util.HibernateUtil; // Assuming you have HibernateUtil
import java.util.List;

public class DeliveryDaoImpl implements DeliveryDao {

    @Override
    public void createDelivery(Delivery delivery) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.persist(delivery); // Save the delivery
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public Delivery getDeliveryById(int deliveryId) {
        Delivery delivery = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            delivery = session.get(Delivery.class, deliveryId); // Get delivery by ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return delivery;
    }

    @Override
    public List<Delivery> getDeliveriesByBookingId(int bookingId) {
        List<Delivery> deliveries = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Delivery> query = session.createQuery("FROM Delivery WHERE bookingId = :bookingId", Delivery.class); // Query by booking ID
            query.setParameter("bookingId", bookingId);
            deliveries = query.list(); // Get list of deliveries
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    @Override
    public List<Delivery> getDeliveriesByCustomerId(int customerId) {
        // Assuming relation to customer through booking
        List<Delivery> deliveries = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Delivery> query = session.createQuery("SELECT d FROM Delivery d JOIN Booking b ON d.bookingId = b.bookingId WHERE b.custId = :customerId", Delivery.class); // Join query to find by customer ID
            query.setParameter("customerId", customerId);
            deliveries = query.list(); // Get list of deliveries
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deliveries;
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.merge(delivery); // Update the delivery
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDelivery(int deliveryId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            Delivery delivery = session.get(Delivery.class, deliveryId); // Get delivery to delete
            if (delivery != null) {
                session.remove(delivery); // Delete the delivery
            }
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public Delivery getDeliveryByBookingId(int bookingId) {
        Delivery delivery = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Delivery> query = session.createQuery("FROM Delivery WHERE bookingId = :bookingId", Delivery.class); // Query by booking ID
            query.setParameter("bookingId", bookingId);
            delivery = query.uniqueResult(); // Get single result
        } catch (Exception e) {
            e.printStackTrace();
        }
        return delivery;
    }
}