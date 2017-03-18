package com.lanzuan.entity.support.field;

import com.lanzuan.entity.support.Item;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public class CarouselCaption implements Item {
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
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

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

    @Override
    public String name() {
        return null;
    }

    @Override
    public String text() {
        return text;
    }

    @Override
    public String image() {
        return null;
    }

    @Override
    public String href() {
        return link;
    }

    @Override
    public String title() {
        return text;
    }

    @Override
    public List<Item> childItems() {
        return null;
    }

    @Override
    public Integer repeatLimit() {
        return 1;
    }
}
