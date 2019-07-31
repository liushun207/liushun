package com.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Object convert util.
 */
// @Log4j2
public class ObjectConvertUtil {

    /**
     * 对象属性转化
     * @param <S> the type parameter
     * @param <R> the type parameter
     * @param source the source
     * @param clazz the clazz
     * @return the list
     */
    public static <S, R> List<R> convert(List<S> source, Class<R> clazz) {
        if(source == null) {
            return null;
        }

        List<R> result = new ArrayList<>();
        try {
            for(S dto : source) {
                R obj = clazz.newInstance();
                BeanUtils.copyProperties(dto, obj);
                result.add(obj);
            }
        }
        catch(Exception e) {
            // log.error("", e);
        }

        return result;
    }

    /**
     * 单对象转list.
     * @param <T> the type parameter
     * @param list the list
     * @return the list
     */
    @SafeVarargs
    public static <T> List<T> toList(T... list) {
        List<T> result = new ArrayList<>();

        Collections.addAll(result, list);

        return result;
    }
}
