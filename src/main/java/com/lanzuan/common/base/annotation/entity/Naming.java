package com.lanzuan.common.base.annotation.entity;

import com.lanzuan.common.code.Expression;

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
    public String ngRepeatVar() default "";
    /**
     * when和expression一起使用，用javascript表达式表示依赖某个字段，用于联动编辑
     * expression可以为空，表示when引用的字段是否存在
     */


    public String when() default "";

    /**
     * 多个表达式用逻辑与关系链接，逻辑或总是可以转换为逻辑与
     * @return
     */
    public Expression expression() default Expression.IS_NOT_EMPTY;
    public String[] params() default {};

}
