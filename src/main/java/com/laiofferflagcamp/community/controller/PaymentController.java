package com.laiofferflagcamp.community.controller;

import com.laiofferflagcamp.community.entity.db.InvoiceItem;
import com.laiofferflagcamp.community.entity.reqeust.PaymentRequestBody;
import com.laiofferflagcamp.community.service.PaymentService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ArrayList<>();
        }
        String userId = (String) session.getAttribute("username");
        return paymentService.getPayments(userId);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getPaymentLink(@RequestParam(value = "invoiceId") String invoiceId, HttpServletRequest request, HttpServletResponse response) throws StripeException {
//        String invoiceId = requestBody.getInvoiceId();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new HashMap<>();
        }
        String userId = (String) session.getAttribute("username");
        String referer = request.getHeader(HttpHeaders.REFERER);
//        String origin = request.getHeader(HttpHeaders.ORIGIN);
        Map<String, String> result = paymentService.getPaymentLink(userId, invoiceId, referer);
        if (result == null){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return result;
    }

    @RequestMapping(value = "/paymentConfirm", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> confirmPayment(HttpServletRequest request, HttpServletResponse response) throws StripeException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new HashMap<>();
        }
        String userId = (String) session.getAttribute("username");
        Map<String, String> result = paymentService.confirmPayment(userId);
        if (result == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return result;
    }
}
