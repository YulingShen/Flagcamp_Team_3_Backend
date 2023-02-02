package com.laiofferflagcamp.community.dao;

import com.laiofferflagcamp.community.entity.db.InvoiceItem;
import com.laiofferflagcamp.community.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class PaymentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<InvoiceItem> getInvoiceItemList(String userId){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(User.class, userId).getInvoiceItems();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return new ArrayList<>();
    }

    public String getPaymentId(String invoiceId){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.get(InvoiceItem.class, invoiceId).getPaymentId();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return null;
    }
}
