package com.laiofferflagcamp.community.service;

import com.laiofferflagcamp.community.dao.MaintenanceDao;
import com.laiofferflagcamp.community.entity.db.Maintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class MaintenanceService {
    @Autowired

    private MaintenanceDao maintenanceDao;
    public void createMaintenance(String userId, Maintenance maintenance) {
        maintenanceDao.createMaintenance(userId, maintenance);
    }


    public void deleteMaintenance(String userId, String mainId) {
        maintenanceDao.deleteMaintenance(userId, mainId);

    }

    public List<Maintenance> getMaintenance(String userId) {
        List<Maintenance> mainList = new ArrayList<>();
        Set<Maintenance> maintenances = maintenanceDao.getMaintenances(userId);
        for(Maintenance maintenance : maintenances) {
            mainList.add(maintenance);
        }
        return mainList;
    }
}
