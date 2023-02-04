package com.laiofferflagcamp.community.controller;

import com.alibaba.fastjson.JSON;
import com.laiofferflagcamp.community.entity.db.Maintenance;
import com.laiofferflagcamp.community.entity.request.MainRequestBody;
import com.laiofferflagcamp.community.service.MaintenanceService;
import com.laiofferflagcamp.community.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    // create new maintenances
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result create(@RequestBody Maintenance maintenance) {
        maintenanceService.createMaintenance(maintenance);
        return new Result(200, null, "save success");
    }

    /**
     * get maintenance list by userId
     * @param userId
     * @date 2023/2/3 9:47
     */
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public Result getList(@PathVariable("userId") long userId) {
        List<Maintenance> maintenanceList = maintenanceService.getMaintenance(userId);
        return new Result(200, maintenanceList, "query success");
    }

    // delete by mainId
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Result delete(@RequestBody Maintenance maintenance) {
        maintenanceService.deleteMaintenance(maintenance.getMainId());
        return new Result(200, null, "delete success");
    }

    /**
     * update
     * @param maintenance
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody Maintenance maintenance) {
        maintenanceService.modify(maintenance);
        return new Result(200, null, "update success");
    }


}
