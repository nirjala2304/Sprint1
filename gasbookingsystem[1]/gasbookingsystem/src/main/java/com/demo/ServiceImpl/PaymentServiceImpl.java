package com.demo.ServiceImpl;

import com.demo.Dao.PaymentDao;
import com.demo.DaoImpl.PaymentDaoImpl;
import com.demo.entity.Payment;
import com.demo.Service.PaymentService;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDao paymentDao = new PaymentDaoImpl();

    @Override
    public Payment getPaymentById(int payId) {
        return paymentDao.getPaymentById(payId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentDao.getAllPayments();
    }

    @Override
    public void createPayment(Payment payment) {
        // You might add business logic here before creating
        paymentDao.createPayment(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        // You might add business logic here before updating
        paymentDao.updatePayment(payment);
    }

    @Override
    public void deletePayment(Payment payment) {
        // You might add business logic here before deleting
        paymentDao.deletePayment(payment);
    }

    @Override
    public Payment getPaymentByBookingId(int bookingId) {
        return paymentDao.getPaymentByBookingId(bookingId);
    }
}