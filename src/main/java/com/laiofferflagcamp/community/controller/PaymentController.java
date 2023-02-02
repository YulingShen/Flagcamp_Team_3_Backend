package com.laiofferflagcamp.community.controller;

import com.laiofferflagcamp.community.entity.db.InvoiceItem;
import com.laiofferflagcamp.community.entity.reqeust.PaymentRequestBody;
import com.laiofferflagcamp.community.service.PaymentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    @ResponseBody
    public List<InvoiceItem> getPaymentList(HttpServletRequest request, HttpServletResponse response){
        return paymentService.getPayments("1");
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getPaymentLink(@RequestParam(value = "invoiceId") String invoiceId, HttpServletRequest request, HttpServletResponse response) throws StripeException {
//        String invoiceId = requestBody.getInvoiceId();
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        return paymentService.getPaymentLink("1", invoiceId, origin);
    }

    @RequestMapping(value = "/paymentConfirm", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> confirmPayment(HttpServletRequest request, HttpServletResponse response) throws StripeException {
        return paymentService.confirmPayment("1");
    }
}
