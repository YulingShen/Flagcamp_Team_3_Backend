package com.laiofferflagcamp.community.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @date 2023/2/3 15:07
 * @project Flagcamp_Team_3_Backend-maintenance
 */
public class Convert {

    /**
     * comment cover
     */
    public static <T> List<T> convert(Class<T> c, List<?> os) {
        List<T> list = new ArrayList<T>();
        for (Object o : os) {
            list.add(convert(c, o));
        }
        return list;
    }

    public static <T> T convert(Class<T> c, Object o) {
        if (null == o) {
            return null;
        }
        T r = null;
        try {
            r = c.newInstance();
            List<Field> aimFs = getFieldList(c);
            List<Field> souFs = getFieldList(o.getClass());
            for (Field f : aimFs) {
                int n = f.getModifiers();
                if (Modifier.isStatic(n) || Modifier.isFinal(n)) {
                    continue;
                }
                f.setAccessible(true);
                Field cf = null;
                for (Field tf : souFs) {
                    if (tf.getName().equals(f.getName())) {
                        cf = tf;
                        break;
                    }
                }
                if (null != cf) {
                    try {
                        cf.setAccessible(true);
                        Object v = cf.get(o);
                        f.setAccessible(true);
                        f.set(r, v);
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;

    }

    /**
     * get class all field
     * @param c
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<Field> getFieldList(Class c) {
        List<Field> fieldList = new ArrayList<>();
        while (c != null) {
            fieldList.addAll(Arrays.asList(c.getDeclaredFields()));
            c = c.getSuperclass();
        }
        return fieldList;
    }
}
