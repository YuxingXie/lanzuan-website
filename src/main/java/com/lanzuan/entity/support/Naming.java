package com.lanzuan.entity.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/3/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD,ElementType.TYPE })
public @interface Naming {
    public String value() default "";
}
