package com.lanzuan.entity.support;

/**
 * Created by Administrator on 2017/3/23.
 */
public enum CarouselCaptionType {
    LINK("link"),
    TEXT("text");
    private String code;
    private CarouselCaptionType(String code) {
        this.code=code;
    }
    public String toCode() {
        return this.code;
    }
    public static CarouselCaptionType fromCode(String code) {
        for (CarouselCaptionType carouselCaptionType : CarouselCaptionType.values()) {
            if (carouselCaptionType.code.equals(code)) {
                return carouselCaptionType;
            }
        }
        return null;
    }

}
