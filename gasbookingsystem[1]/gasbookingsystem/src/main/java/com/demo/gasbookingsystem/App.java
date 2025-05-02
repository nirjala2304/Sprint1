package com.demo.gasbookingsystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.demo.entity.Admin;
import Util.HibernateUtil;

public class App {
    public static void main(String[] args) {

        //get sessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();
        //create a session(connection)
        Session session = factory.openSession();

        //Begin a Transaction
        Transaction tx = session.beginTransaction();
        tx.commit(); // Commit the transaction
        session.close(); // Close the session
        factory.close(); // Close the session factory
    }
}