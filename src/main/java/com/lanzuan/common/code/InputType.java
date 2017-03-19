package com.lanzuan.common.code;

/**
 * Created by Administrator on 2017/3/19.
 */
public enum InputType {
    SELECT("select"),
    CHECKBOX("checkbox"),
    RADIO("radio"),
    COLOR("color"),
    DATE("date"),
    DATETIME("datetime"),
    DATETIME_LOCAL("datetime-local"),
    FILE("file"),
    IMAGE("image"),
    MONTH("month"),
    NUMBER("number"),
    PASSWORD("password"),
    RANGE("range"),
    SEARCH("search"),
    TEL("tel"),
    TEX("tex"),
    TIME("time"),
    WEEK("week"),
    URL("url"),EMAIL("email"),
    TEXT("text"),
    TOGGLE_BOOLEAN("toggle-boolean");


    private String code;
    private InputType(String code) {
        this.code=code;
    }
    public String toCode() {
        return this.code;
    }
    public static InputType fromCode(String code) {
        for (InputType inputType : InputType.values()) {
            if (inputType.code.equals(code)) {
                return inputType;
            }
        }
        return null;
    }
}
