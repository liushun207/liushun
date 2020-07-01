package com.guava;

import com.google.common.base.Function;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * CarFunction
 * @author liushun
 * @since JDK 1.8
 **/
public class CarFunction implements Function<String, String> {
    @Nullable
    @Override
    public String apply(@Nullable String s) {
        return "hello " + s;
    }
}
