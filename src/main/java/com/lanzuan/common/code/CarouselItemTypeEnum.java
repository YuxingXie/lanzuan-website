package com.lanzuan.common.code;

/**
 * 通知类型
 */
public enum CarouselItemTypeEnum {

    /**
     * text,link,image
     */
    IMAGE("image"),
    LINK("link"),
    TEXT("text");
    private String code;
    private CarouselItemTypeEnum(String code) {
        this.code=code;
    }
    public String toCode() {
        return this.code;
    }
    public static CarouselItemTypeEnum fromCode(String code) {
        for (CarouselItemTypeEnum notifyTypeCodeEnum : CarouselItemTypeEnum.values()) {
            if (notifyTypeCodeEnum.code.equals(code)) {
                return notifyTypeCodeEnum;
            }
        }
        return null;
    }
}
