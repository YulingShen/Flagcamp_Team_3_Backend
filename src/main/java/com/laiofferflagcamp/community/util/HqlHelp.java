package com.laiofferflagcamp.community.util;

import com.laiofferflagcamp.community.dao.BaseDao;
import org.hibernate.Query;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date 2023/2/3 11:23
 * @project Flagcamp_Team_3_Backend-maintenance
 */
public class HqlHelp {
    /**
     * 统一的jap方式Query
     *
     * @param dao
     * @param hql
     * @param paras
     * @return
     */
    public static Query getQuery(BaseDao dao, String hql, Object[] paras) {
        String jpaHql = HqlHelp.toJpaHql(hql);
        Query query = dao.getSession().createQuery(jpaHql);
        Object para;
        if (null != paras) {
            for (int i = 0; i < paras.length; i++) {
                String zw = "a" + i;
                para = paras[i];
                if(null == para){
                    query.setParameter(zw, para);
                    continue;
                }
                if(para.getClass().isArray()){
                    query.setParameterList(zw,(Object[])para);
                }else if(para instanceof Collection){
                    query.setParameterList(zw, (Collection) para);
                }else if(para instanceof Date){
                    query.setTimestamp(zw, (Date) para);
                }else{
                    query.setParameter(zw, para);
                }
            }
        }
        return query;
    }

    public static Query getSqlQuery(BaseDao dao, String sql, Object[] paras) {
        String jpaSql = HqlHelp.toJpaHql(sql);
        Query query = dao.getSession().createSQLQuery(jpaSql);
        if (null != paras) {
            for (int i = 0; i < paras.length; i++) {
                String zw = "a" + i;
                query.setParameter(zw, paras[i]);
            }
        }
        return query;
    }

    /**
     * 转换老式?占为符hql为JPA占位符方式
     *
     * @param hql
     * @return
     */
    public static String toJpaHql(String hql) {
        Matcher m = Pattern.compile("\\?").matcher(hql);
        StringBuffer str = new StringBuffer();
        int n = 0;
        while (m.find()) {
            String zw = ":a" + n++;
            // 替换并添加到str字符串
            m.appendReplacement(str, zw);
        }
        // 将剩余的字符串添加到str字符串
        m.appendTail(str);
        return str.toString();
    }
}
