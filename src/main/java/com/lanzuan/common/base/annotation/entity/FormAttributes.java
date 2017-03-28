package com.lanzuan.common.base.annotation.entity;

import com.lanzuan.common.code.InputType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/3/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD ,ElementType.TYPE})
public @interface FormAttributes {
    public InputType inputType() default InputType.TEXT;
    public String[] optionValues() default {};
    public String imageUploadDir() default "";
    public String newAction() default "";
    public String editAction() default "";
    public String modelName() default "";
    public boolean required() default false;
}
