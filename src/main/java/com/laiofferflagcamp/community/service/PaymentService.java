package com.laiofferflagcamp.community.service;

import com.laiofferflagcamp.community.dao.PaymentDao;
import com.laiofferflagcamp.community.entity.db.InvoiceItem;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stripe.Stripe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    private String apiKey = "sk_test_51MMMzrA6Uv3U6WAeDHAnQ2Y0QTirUpVwsDEsd2IxE0MHsuM7hORRhYmu3zQkAYQA59HMoxmfO6NWKfZB7UZfsFa100HDiZANI9";

    public List<InvoiceItem> getPayments(String userId){
        return paymentDao.getInvoiceItemList(userId);
    }

    public Map<String, String> getPaymentLink(String userId, String invoiceId) throws StripeException {
        Stripe.apiKey = apiKey;
        String paymentId = paymentDao.getPaymentId(invoiceId);
        Invoice invoice = Invoice.retrieve(paymentId);
        String paymentUrl = invoice.getHostedInvoiceUrl();
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("stripe_url", paymentUrl);
        return hashMap;
    }
}
