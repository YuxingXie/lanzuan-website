package com.lanzuan.entity.support;

/**
 * Created by Administrator on 2017/3/10.
 */
public class CarouselCaption {
    //暂时仅{link，text}，应该弄个枚举的
    private String type;
    /**
     * 如果type为link，则需要value表示链接地址，text表示链接文字，如果type为text,则没有value只有text
     */
    private String  value;
    private String text;
    /**
     * 标题css样式名
     */
    private String captionClass;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCaptionClass() {
        return captionClass;
    }

    public void setCaptionClass(String captionClass) {
        this.captionClass = captionClass;
    }
}
