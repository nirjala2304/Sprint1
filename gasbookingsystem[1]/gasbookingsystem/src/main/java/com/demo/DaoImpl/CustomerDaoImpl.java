package com.demo.DaoImpl;

import com.demo.Dao.CustomerDao;
import com.demo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Util.HibernateUtil;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void createCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.persist(customer); // Save the customer
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Customer.class, customerId); // Get customer by ID
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteCustomer(int customerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            Customer customer = session.get(Customer.class, customerId); // Get customer to delete
            if (customer != null) {
                session.remove(customer); // Delete the customer
            }
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.merge(customer); // Update the customer
            transaction.commit(); // Commit transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Customer", Customer.class).list(); // Get all customers
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}