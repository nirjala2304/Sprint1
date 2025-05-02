package com.demo.DaoImpl;

import com.demo.Dao.AdminDao;
import com.demo.entity.Admin;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AdminDaoImpl implements AdminDao {

    @Override
    public Admin getAdminById(int adminId) {
        Admin admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            admin = session.get(Admin.class, adminId); // Retrieve Admin by ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public Admin getAdminByName(String adminName) {
        Admin admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> query = session.createQuery("FROM Admin WHERE adminName = :adminName", Admin.class); // HQL query to find Admin by name
            query.setParameter("adminName", adminName);
            admin = query.uniqueResult(); // Get the single result
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public Admin getAdminByNameAndPassword(String adminName, String adminPassword) {
        Admin admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> query = session.createQuery("FROM Admin WHERE adminName = :adminName AND adminPassword = :adminPassword", Admin.class); // HQL query to find Admin by name and password
            query.setParameter("adminName", adminName);
            query.setParameter("adminPassword", adminPassword);
            admin = query.uniqueResult(); // Get the single result
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}