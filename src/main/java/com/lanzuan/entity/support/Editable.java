package com.lanzuan.entity.support;

import com.lanzuan.common.code.InputType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/3/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Editable{
    public InputType inputType() default InputType.TEXT;
    public String[] optionValues() default {};

}
