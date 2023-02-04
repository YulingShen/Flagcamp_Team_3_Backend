package com.laiofferflagcamp.community.service;

import com.laiofferflagcamp.community.dao.PaymentDao;
import com.laiofferflagcamp.community.entity.db.InvoiceItem;
import com.stripe.exception.StripeException;
//import com.stripe.model.Invoice;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.model.checkout.Session;
import com.stripe.param.ProductUpdateParams;
import com.stripe.param.checkout.SessionCreateParams;
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

    public Map<String, String> getPaymentLink(String userId, String invoiceId, String origin) throws StripeException {
        Stripe.apiKey = apiKey;
        String paymentId = paymentDao.getPaymentId(userId, invoiceId);
        if (paymentId == null) {return null;}
//        Invoice invoice = Invoice.retrieve(paymentId);
//        String paymentUrl = invoice.getHostedInvoiceUrl();
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(origin + "?success=true")
                        .setCancelUrl(origin + "?cancel=true")
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
                                        .setPrice(paymentId)
                                        .build())
                        .build();
        Session session = Session.create(params);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("stripe_url", session.getUrl());
        return hashMap;
    }

    public Map<String, String> confirmPayment(String userId) throws StripeException {
        Map<String, String> hashMap = new HashMap<>();
        String invoiceId = paymentDao.getPreviousInvoiceId();
        Boolean result = paymentDao.invoiceStateChange(userId, invoiceId, "paid");
        if (!result) {
            return null;
        }
        hashMap.put("success", result.toString());
        //archive the corresponding product in case of futher mis-payment
        Stripe.apiKey = apiKey;
        String paymentId = paymentDao.getPaymentId(userId, invoiceId);
        Price price = Price.retrieve(paymentId);
        Product product = Product.retrieve(price.getProduct());
//        Map<String, Object> params = new HashMap<>();
//        params.put("product", null);
//        Price updatedPrice = price.update(params);
//        if (!updatedPrice.getDeleted()){
//            hashMap.put("success", "Price update failed");
//        }
//        Map<String, Object> productMap = new HashMap<>();
//        productMap.put("default_price", null);
//        Product updatedProduct = product.update(productMap);
        ProductUpdateParams params = ProductUpdateParams.builder().setActive(false).build();

        Product updatedProduct = product.update(params);
//        Product deletedProduct = product.delete();
        if (updatedProduct.getActive()){
            hashMap.put("success", "Product archive failed");
        }
        return hashMap;
    }
}
