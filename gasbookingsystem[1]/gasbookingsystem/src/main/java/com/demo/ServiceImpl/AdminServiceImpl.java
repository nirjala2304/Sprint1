package com.demo.ServiceImpl;

import com.demo.Service.AdminService;
import com.demo.entity.Admin;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    @Override
    public Admin getAdminById(int adminId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Admin.class, adminId);
        } catch (Exception e) {
            System.err.println("Error getting admin by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Admin getAdminByName(String adminName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> query = session.createQuery("FROM Admin WHERE adminName = :adminName", Admin.class);
            query.setParameter("adminName", adminName);
            List<Admin> admins = query.list();
            return admins.isEmpty() ? null : admins.get(0); // Assuming unique admin names
        } catch (Exception e) {
            System.err.println("Error getting admin by name: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(admin);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error creating admin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(admin);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error updating admin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(int adminId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Admin admin = session.get(Admin.class, adminId);
            if (admin != null) {
                session.remove(admin);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error deleting admin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Admin getAdminByNameAndPassword(String adminName, String adminPassword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> query = session.createQuery("FROM Admin WHERE adminName = :adminName AND adminPassword = :adminPassword", Admin.class);
            query.setParameter("adminName", adminName);
            query.setParameter("adminPassword", adminPassword);
            List<Admin> admins = query.list();
            return admins.isEmpty() ? null : admins.get(0);
        } catch (Exception e) {
            System.err.println("Error getting admin by name and password: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Admin", Admin.class).list();
        } catch (Exception e) {
            System.err.println("Error getting all admins: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}