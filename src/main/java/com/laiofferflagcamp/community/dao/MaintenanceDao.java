package com.laiofferflagcamp.community.dao;

import com.laiofferflagcamp.community.entity.db.Maintenance;
import com.laiofferflagcamp.community.entity.db.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MaintenanceDao extends BaseDao {

    // Insert a maintenance request record to the database
    public void createMaintenance(Maintenance maintenance) {
        create(maintenance);
    }

    // Remove a maintenance record from the database
    public void deleteMaintenance(long mainId) {
        execHql("delete from " + Maintenance.class.getName() + " where main_id = ?", new Object[]{mainId});
        // del(Maintenance.class, "main_id", mainId);
    }

    // Get Maintenance requests
    public List<Maintenance> getMaintenances(long userId) {
        List<Maintenance> list = queryList(Maintenance.class ,"from "+ Maintenance.class.getName() +" where user_id = ? ", new Object[]{userId});
        return list;
    }

    // update
    public void updateMaintenance(Maintenance maintenance) {
        update(maintenance);
    }

    public Set<String> getFavoriteItemIds(String userId) {
        //Set<String> itemIds = new HashSet<>();
        //
        //try (Session session = sessionFactory.openSession()) {
        //    Set<Maintenance> maintenances = session.get(User.class, userId).getMainSet();
        //    for (Maintenance maintenance : maintenances) {
        //        itemIds.add(maintenance.getMainId());
        //    }
        //} catch (Exception ex) {
        //    ex.printStackTrace();
        //}
        //return itemIds;
        return null;
    }


}
