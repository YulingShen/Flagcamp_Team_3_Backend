package com.laiofferflagcamp.community.controller;

import com.laiofferflagcamp.community.entity.db.User;
import com.laiofferflagcamp.community.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void register(@RequestBody User user, HttpServletResponse response) throws IOException {
        String responseString = "success";
        if (!registerService.register(user)) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            responseString = "fail";
        }
//        return responseString;
    }
}