package com.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

// @Log4j2
public class ObjectCheckUtil {
    /**
     * 判断对象中属性值是否全为空
     * @param object
     * @return
     */
    public static boolean checkObjAllFieldsIsNull(Object object) {
        if(object == null) {
            return true;
        }

        try {
            for(Field f : object.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if(f.get(object) != null && StringUtils.isNotBlank(f.get(object).toString())) {
                    return false;
                }
            }
        }
        catch(Exception e) {
            // log.error("", e);
        }

        return true;
    }
}
