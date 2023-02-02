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

    private String previousInvoiceId = null;

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

    public String getPaymentId(String userId, String invoiceId){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            previousInvoiceId = invoiceId;
            InvoiceItem item = session.get(InvoiceItem.class, invoiceId);
            if (item.getPayee().getUserId().equals(userId)) {
                return item.getPaymentId();
            }
            else{return null;}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return null;
    }

    public String getPreviousInvoiceId(){
        return previousInvoiceId;
    }

    public Boolean invoiceStateChange(String userId, String invoiceId, String status){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            User user = session.get(User.class, userId);
            InvoiceItem item = session.get(InvoiceItem.class, invoiceId);
            if (item.getPayee().getUserId().equals(userId)) {
                item.setStatus(status);
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();
                return true;
            }
            else{return false;}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return false;
    }
}
