package com.laiofferflagcamp.community.dao;

import com.laiofferflagcamp.community.entity.db.Maintenance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository

public class MaintenanceDao {
        @Autowired
        private SessionFactory sessionFactory;

        // Insert a maintenance request record to the database
        public void createMaintenance(String userId, Maintenance maintenance) {
            Session session = null;

            try {
                session = sessionFactory.openSession();
                User user = session.get(User.class, userId);
                user.getMainSet().add(maintenance);
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                if (session != null) session.close();
            }
        }

        // Remove a maintenance record from the database
        public void deleteMaintenance(String userId, String mainId) {

            Session session = null;
            try {
                session = sessionFactory.openSession();
                User user = session.get(User.class, userId);
                Maintenance maintenance = session.get(Maintenance.class, mainId);
                user.getMainSet().remove(mainId);
                session.beginTransaction();
                session.update(user);
                session.getTransaction().commit();

            } catch (Exception ex) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            } finally {
                if (session != null) session.close();
            }

        }

        // Get Maintenance requests

        public Set<Maintenance> getMaintenances(String userId) {
            Session session = null;

            try {
                session = sessionFactory.openSession();
                return session.get(User.class, userId).getMainSet();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (session != null) session.close();
            }
            return new HashSet<>();
        }



        public Set<String> getFavoriteItemIds(String userId) {
            Set<String> itemIds = new HashSet<>();

            try (Session session = sessionFactory.openSession()) {
                Set<Maintenance> maintenances = session.get(User.class, userId).getMainSet();
                for(Maintenance maintenance : maintenances) {
                    itemIds.add(maintenance.getMaintenanceId());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return itemIds;
        }

        // Get favorite items for the given user. The returned map includes three entries like {"Video": [item1, item2, item3], "Stream": [item4, item5, item6], "Clip": [item7, item8, ...]}
//    public Map<String, List<String>> getFavoriteGameIds(Set<String> favoriteItemIds) {
//        Map<String, List<String>> itemMap = new HashMap<>();
//        for (ItemType type : ItemType.values()) {
//            itemMap.put(type.toString(), new ArrayList<>());
//        }
//
//        try (Session session = sessionFactory.openSession()) {
//            for(String itemId : favoriteItemIds) {
//                Item item = session.get(Item.class, itemId);
//                itemMap.get(item.getType().toString()).add(item.getGameId());
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return itemMap;
//    }

//        public Map<String, List<String>> getFavoriteGameIds(Set<Maintenance> maintenances) {
//            Map<String, List<String>> itemMap = new HashMap<>();
//            for (ItemType type : ItemType.values()) {
//                itemMap.put(type.toString(), new ArrayList<>());
//            }
//            for (Item item : items) {
//                itemMap.get(item.getType().toString()).add(item.getGameId());
//            }
//            return itemMap;
//        }



}
