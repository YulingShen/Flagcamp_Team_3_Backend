package com.laiofferflagcamp.community.controller;

import com.laiofferflagcamp.community.entity.db.InvoiceItem;
import com.laiofferflagcamp.community.entity.reqeust.PaymentRequestBody;
import com.laiofferflagcamp.community.service.PaymentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    @ResponseBody
    public List<InvoiceItem> getPaymentList(HttpServletRequest request, HttpServletResponse response){
        return paymentService.getPayments("1");
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> getPaymentLink(@RequestBody PaymentRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) throws StripeException {
        String invoiceId = requestBody.getInvoiceId();
        return paymentService.getPaymentLink("1", invoiceId);
    }
}
