package com.demo.DaoImpl;

import com.demo.Dao.DetailsDao;
import com.demo.entity.Details;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class DetailsDaoImpl implements DetailsDao {

    @Override
    public Details getDetailsById(int detailsId) {
        Details details = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            details = session.get(Details.class, detailsId); // Get Details by ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return details;
    }

    @Override
    public List<Details> getAllDetails() {
        List<Details> detailsList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Details> query = session.createQuery("FROM Details", Details.class); // HQL query to get all Details
            detailsList = query.list(); // Get the list of all Details
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailsList;
    }

    @Override
    public List<Details> getDetailsByBookingId(int bookingId) {
        List<Details> detailsList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Details> query = session.createQuery("FROM Details WHERE bookingId = :bookingId", Details.class); // HQL query to find Details by booking ID
            query.setParameter("bookingId", bookingId); // Set the booking ID parameter
            detailsList = query.list(); // Get the list of Details for the given booking ID
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailsList;
    }

    // Removed create, update, delete for now
    // @Override
    // public void createDetails(Details details) { ... }
    //
    // @Override
    // public void updateDetails(Details details) { ... }
    //
    // @Override
    // public void deleteDetails(Details details) { ... }
}