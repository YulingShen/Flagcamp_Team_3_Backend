package com.laiofferflagcamp.community.controller;

import com.laiofferflagcamp.community.entity.db.Maintenance;
import com.laiofferflagcamp.community.entity.request.MainRequestBody;
import com.laiofferflagcamp.community.service.MaintenanceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller

public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    // create new maintenances

    @RequestMapping(value = "/maintenance", method = RequestMethod.POST)
    public void createMaintenance(
            @RequestBody MainRequestBody requestBody,
            HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return ;
        }
        String userId = (String) session.getAttribute("user_id");

        maintenanceService.createMaintenance(userId, requestBody.getMaintenance());

    }

    // get
    @RequestMapping(value = "/maintenance", method = RequestMethod.GET)
    @ResponseBody
    public List<Maintenance> getMaintenanceItems(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new ArrayList<>();
        }
        String userId = (String) session.getAttribute("user_id");
        return maintenanceService.getMaintenance(userId);
    }

    // delete
    @RequestMapping(value = "/maintenanceUpdate", method = RequestMethod.DELETE)
    public void deleteMaintenance(@RequestBody MainRequestBody requestBody, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        String userId = (String) session.getAttribute("user_id");
        maintenanceService.deleteMaintenance(userId, requestBody.getMaintenance().getMaintenanceId());
    }

    // update
    @RequestMapping(value = "/maintenanceUpdate", method = RequestMethod.POST)
    public void createMaintenance(
            @RequestBody MainRequestBody requestBody,
            HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return ;
        }
        String userId = (String) session.getAttribute("user_id");

        maintenanceService.createMaintenance(userId, requestBody.getMaintenance());

    }


}
