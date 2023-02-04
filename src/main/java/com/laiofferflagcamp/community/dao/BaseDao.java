package com.laiofferflagcamp.community.dao;

import com.laiofferflagcamp.community.util.Convert;
import com.laiofferflagcamp.community.util.HqlHelp;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @date 2023/2/3 11:24
 * @project Flagcamp_Team_3_Backend-maintenance
 */
public class BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // execute hql
    public int execHql(String hql, Object[] paras) {
        Query query = HqlHelp.getQuery(this, hql, paras);
        return query.executeUpdate();
    }

    // execute sql
    public List querySql(String sql, Object[] paras) {
        Query query = HqlHelp.getSqlQuery(this, sql, paras);
        return query.list();
    }
    // query list by params
    public <M> List<M> queryList(Class<M> vo, String hql, Object[] paras) {
        return queryList(vo, hql, paras, -1, 0);
    }

    // delete
    public <M> void del(Class<M> en, String idName, long idValue) {
        String hql = "delete from " + en.getName() + " where " + idName + " = ?";
        execHql(hql, new Object[] { idValue });
    }

    // create
    public void create(Object o) {
        getSession().save(o);
    }

    // update
    public void update(Object o) {
        getSession().update(o);
    }

    /**
     * query list
     * @param vo
     * @param hql sql
     * @param paras
     * @param firstResult 起始条数，从0开始， -1表示不分页
     * @param maxResult 最大返回条数
     * @date 2023/2/3 15:13
     */
    public <M> List<M> queryList(Class<M> vo, String hql, Object[] paras, int firstResult, int maxResult) {
        Query query = HqlHelp.getQuery(this, hql, paras);
        if (-1 != firstResult) {
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
        }
        List list = query.list();
        return null == vo ? list : Convert.convert(vo, list);
    }
}
