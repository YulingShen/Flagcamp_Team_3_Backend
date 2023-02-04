package com.laiofferflagcamp.community.service;

import com.laiofferflagcamp.community.dao.MaintenanceDao;
import com.laiofferflagcamp.community.entity.db.Maintenance;
import com.laiofferflagcamp.community.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = false)
public class MaintenanceService {
    @Autowired

    private MaintenanceDao maintenanceDao;

    /**
     * create maintenance
     * @param maintenance
     * @date 2023/2/3 16:13
     */
    public void createMaintenance(Maintenance maintenance) {
        maintenanceDao.createMaintenance(maintenance);
    }

    /**
     * delete maintenance by mainId
     * @param mainId
     * @date 2023/2/3 16:12
     */
    public void deleteMaintenance(long mainId) {
        maintenanceDao.deleteMaintenance(mainId);

    }

    /**
     * query maintenanceList by userId
     * @param userId
     * @date 2023/2/3 14:17
     */
    public List<Maintenance> getMaintenance(long userId) {
        List<Maintenance> mainList = maintenanceDao.getMaintenances(userId);
        return mainList;
    }

    /**
     * update date by mainId
     * @param maintenance
     */
    public void modify(Maintenance maintenance) {
        maintenanceDao.update(maintenance);
    }
}
