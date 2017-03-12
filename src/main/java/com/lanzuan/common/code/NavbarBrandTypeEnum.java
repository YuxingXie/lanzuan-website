package com.lanzuan.common.code;

/**
 * 通知类型
 */
public enum NavbarBrandTypeEnum {

    /**
     * text,link,image
     */
    IMAGE("image"),
    TEXT("text");
    private String code;
    private NavbarBrandTypeEnum(String code) {
        this.code=code;
    }
    public String toCode() {
        return this.code;
    }
    public static NavbarBrandTypeEnum fromCode(String code) {
        for (NavbarBrandTypeEnum notifyTypeCodeEnum : NavbarBrandTypeEnum.values()) {
            if (notifyTypeCodeEnum.code.equals(code)) {
                return notifyTypeCodeEnum;
            }
        }
        return null;
    }
}
