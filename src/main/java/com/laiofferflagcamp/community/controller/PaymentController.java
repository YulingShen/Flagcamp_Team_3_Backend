package com.laiofferflagcamp.community.controller;

import com.laiofferflagcamp.community.entity.db.PaymentItem;
import com.laiofferflagcamp.community.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/favorite", method = RequestMethod.POST)
    @ResponseBody
    public List<PaymentItem> getPaymentList(HttpServletRequest request, HttpServletResponse response){
        return paymentService.getPayments("");
    }
}
