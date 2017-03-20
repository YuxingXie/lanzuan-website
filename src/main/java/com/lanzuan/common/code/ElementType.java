package com.lanzuan.common.code;

/**
 * Created by Administrator on 2017/3/19.
 */
public enum ElementType {
    INPUT("input"),
    DIV("div"),
    LABEL("label"),
    BUTTON("button"),
    A("a"),
    NAV("nav"),
    SPAN("span");
    private String code;
    private ElementType(String code) {
        this.code=code;
    }
    public String toCode() {
        return this.code;
    }
    public static ElementType fromCode(String code) {
        for (ElementType inputType : ElementType.values()) {
            if (inputType.code.equals(code)) {
                return inputType;
            }
        }
        return null;
    }
}
