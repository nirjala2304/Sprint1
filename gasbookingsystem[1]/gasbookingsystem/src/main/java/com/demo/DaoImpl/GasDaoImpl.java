package com.demo.DaoImpl;

import com.demo.Dao.GasDao;
import com.demo.entity.Gas;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import Util.HibernateUtil;

public class GasDaoImpl implements GasDao {

    @Override
    public void createGas(Gas gas) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.save(gas); // Save the gas
            transaction.commit(); // Commit transaction
            System.out.println("Gas added successfully.");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("Error adding gas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Gas getGasById(int gasId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Gas.class, gasId); // Get gas by ID
        } catch (HibernateException e) {
            System.err.println("Error retrieving gas: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateGas(Gas gas) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.update(gas); // Update the gas
            transaction.commit(); // Commit transaction
            System.out.println("Gas updated successfully.");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("Error updating gas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGas(int gasId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            Gas gas = session.get(Gas.class, gasId); // Get gas to delete
            if (gas != null) {
                session.delete(gas); // Delete the gas
            }
            transaction.commit(); // Commit transaction
            System.out.println("Gas deleted successfully.");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("Error deleting gas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Gas> getAvailableGases() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Gas> availableGases = session.createQuery("FROM Gas WHERE isAvailable = true", Gas.class).list(); // Get all available gases
            if (availableGases.isEmpty()) {
                System.out.println("GasDaoImpl: No available gases found in database.");
            }
            return availableGases;
        } catch (HibernateException e) {
            System.err.println("GasDaoImpl: Hibernate error getting available gases: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        } catch (Exception e) {
            System.err.println("GasDaoImpl: General error getting available gases: " + e.getMessage());
            e.printStackTrace();
            return java.util.Collections.emptyList();
        }
    }


    @Override
    public void updateGasAvailability(int gasId, boolean isAvailable) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            Gas gas = session.get(Gas.class, gasId); // Get the gas to update
            if (gas != null) {
                gas.setAvailable(isAvailable); // Set the availability
                session.update(gas); // Update the gas
            }
            transaction.commit(); // Commit transaction
            // System.out.println("Gas availability updated successfully.");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            System.err.println("Error updating gas availability: " + e.getMessage());
            e.printStackTrace();
        }
    }
}