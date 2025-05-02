package com.demo.DaoImpl;

import com.demo.Dao.PaymentDao;
import com.demo.entity.Payment;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    @Override
    public Payment getPaymentById(int payId) {
        Payment payment = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            payment = session.get(Payment.class, payId); // Get Payment by ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> payments = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Payment> query = session.createQuery("FROM Payment", Payment.class); // HQL query to get all Payments
            payments = query.list(); // Get the list of all Payments
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public void createPayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.save(payment); // Save the Payment object
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.update(payment); // Update the Payment object
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public void deletePayment(Payment payment) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.delete(payment); // Delete the Payment object
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public Payment getPaymentByBookingId(int bookingId) {
        Payment payment = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Payment> query = session.createQuery("FROM Payment WHERE bookingId = :bookingId", Payment.class); // HQL query to find Payment by booking ID
            query.setParameter("bookingId", bookingId); // Set the booking ID parameter
            payment = query.uniqueResult(); // Get the single result
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }
}