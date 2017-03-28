package com.lanzuan.common.base.annotation.entity;

import com.lanzuan.common.code.InputType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/3/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ListColumn {
    public String fieldOfValue() default "";
    public String columnName() default "";
    public InputType inputType() default InputType.TEXT;
}
