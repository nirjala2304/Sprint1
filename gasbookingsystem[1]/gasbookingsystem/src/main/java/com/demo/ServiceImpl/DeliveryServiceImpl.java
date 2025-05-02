package com.demo.ServiceImpl;

import com.demo.Service.DeliveryService;
import com.demo.entity.Delivery;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DeliveryServiceImpl implements DeliveryService {

    @Override
    public void createDelivery(Delivery delivery) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(delivery);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Delivery getDeliveryById(int deliveryId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Delivery.class, deliveryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Delivery> getDeliveriesByBookingId(int bookingId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Delivery> query = session.createQuery("from Delivery where bookingId = :bookingId", Delivery.class);
            query.setParameter("bookingId", bookingId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Delivery> getDeliveriesByCustomerId(int customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Delivery> query = session.createQuery("select d from Delivery d join Booking b on d.bookingId = b.bookingId where b.custId = :customerId", Delivery.class);
            query.setParameter("customerId", customerId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateDelivery(Delivery delivery) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(delivery);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDelivery(int deliveryId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Delivery delivery = session.get(Delivery.class, deliveryId);
            if (delivery != null) {
                session.delete(delivery);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Delivery getDeliveryByBookingId(int bookingId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Delivery> query = session.createQuery("from Delivery where bookingId = :bookingId", Delivery.class);
            query.setParameter("bookingId", bookingId);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Delivery> query = session.createQuery("from Delivery", Delivery.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}