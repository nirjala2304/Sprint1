package Util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.demo.entity.Admin;
import com.demo.entity.Booking;
import com.demo.entity.Customer;
import com.demo.entity.Delivery;
import com.demo.entity.Gas;
import com.demo.entity.Payment;

public class HibernateUtil {

    private final static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("Hibernate.cfg.xml")
                    .addAnnotatedClass(Admin.class) // Map Admin entity
                    .addAnnotatedClass(Customer.class) // Map Customer entity
                    .addAnnotatedClass(Booking.class) // Map Booking entity
                    .addAnnotatedClass(Gas.class) // Map Gas entity
                    .addAnnotatedClass(Payment.class) // Map Payment entity
                    .addAnnotatedClass(Delivery.class) // Map Delivery entity
                    .buildSessionFactory(); // Build the SessionFactory
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e); // Handle initialization errors
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory; // Get the SessionFactory instance
    }

    public static Session getSession() {
        return getSessionFactory().openSession(); // Open a new session
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close(); // Close the SessionFactory
        }
    }
}